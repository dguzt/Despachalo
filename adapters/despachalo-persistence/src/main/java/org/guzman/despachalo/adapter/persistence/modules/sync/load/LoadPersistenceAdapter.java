package org.guzman.despachalo.adapter.persistence.modules.sync.load;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.helpers.FileHelper;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.load.application.port.out.*;
import org.guzman.despachalo.core.sync.load.application.processors.DataFileProcessor;
import org.guzman.despachalo.core.sync.load.domain.Load;
import org.guzman.despachalo.core.sync.load.domain.LoadState;
import org.guzman.despachalo.core.sync.load.domain.LoadToRegister;
import org.guzman.despachalo.core.sync.load.domain.SyncDetails;
import org.guzman.despachalo.external.awss3.AwsStorageExternalService;
import org.springframework.data.domain.PageRequest;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadPersistenceAdapter implements
        GetPaginatedLoadsPort,
        GetLoadFilePort,
        StoreLoadFilePort,
        RegisterLoadForSyncPort,
        GetSyncDetailsPort,
        RegisterSyncResultPort,
        GetNextSyncPort {

    private static final String LOADS_PATH = "loads/";
    private final AwsStorageExternalService awsStorageExternalService;
    private final SyncRepository syncRepository;
    private final LoadMapper loadMapper;
    private final FileHelper fileHelper;

    @Override
    public Paginator<Load> getPage(Filters filters, String state) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = syncRepository.findAllByStateContainsOrderBySyncAtDesc(state, pageable);

        var data = page.getContent()
                .stream()
                .map(loadMapper::toLoad)
                .collect(Collectors.toList());

        return Paginator.<Load>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }

    @Override
    public String storeLoadFile(File file) {
        return awsStorageExternalService.saveFile(file, LOADS_PATH);
    }

    @Override
    public void registerLoadForSync(LoadToRegister toRegister) {
        var row = loadMapper.toEntity(toRegister);
        syncRepository.save(row);
    }

    @Override
    public Optional<SyncDetails> getSyncDetails(Long syncId) {
        return syncRepository
                .findById(syncId)
                .map(loadMapper::toSyncDetails);
    }

    @Override
    public File getLoadFile(String uri) throws IOException {
        var in = awsStorageExternalService.getFileIn(uri);
        return fileHelper.inputStreamToTmpFile(in);
    }

    @Override
    public void registerSyncResult(Long syncId, String state, DataFileProcessor.Results results) {
        syncRepository.findById(syncId).ifPresent(r -> {
            r.setOkRows(results.getOk());
            r.setErrorRows(results.getError());
            r.setState(state);
            syncRepository.save(r);
        });
    }

    @Override
    public void registerSyncResult(Long syncId, String state) {
        syncRepository.findById(syncId).ifPresent(r -> {
            r.setState(state);
            syncRepository.save(r);
        });
    }

    @Override
    public Optional<Long> getNextSync() {
        var processing = syncRepository.findFirstByState(LoadState.PROCESSING);
        if (processing.isPresent()) {
            return Optional.empty();
        }

        return syncRepository.findTopByStateOrderBySyncAt(LoadState.PENDING).map(SyncEntity::getId);
    }
}
