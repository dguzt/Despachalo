package org.guzman.despachalo.adapter.persistence.modules.sync.load;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.load.application.port.out.GetPaginatedLoadsPort;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterLoadForSyncPort;
import org.guzman.despachalo.core.sync.load.application.port.out.StoreLoadFilePort;
import org.guzman.despachalo.core.sync.load.domain.Load;
import org.guzman.despachalo.core.sync.load.domain.LoadToRegister;
import org.guzman.despachalo.external.awss3.AwsStorageExternalService;
import org.springframework.data.domain.PageRequest;

import java.io.File;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadPersistenceAdapter implements GetPaginatedLoadsPort, StoreLoadFilePort, RegisterLoadForSyncPort {
    private final AwsStorageExternalService awsStorageExternalService;
    private final SyncRepository syncRepository;
    private final LoadMapper loadMapper;

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
        var loadPath = "loads/";
        return awsStorageExternalService.saveFile(file, loadPath);
    }

    @Override
    public void registerLoadForSync(LoadToRegister toRegister) {
        var row = loadMapper.toEntity(toRegister);
        syncRepository.save(row);
    }
}
