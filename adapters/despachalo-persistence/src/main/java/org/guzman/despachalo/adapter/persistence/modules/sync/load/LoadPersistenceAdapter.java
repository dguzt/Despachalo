package org.guzman.despachalo.adapter.persistence.modules.sync.load;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.load.application.port.out.GetPaginatedLoadsPort;
import org.guzman.despachalo.core.sync.load.domain.Load;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadPersistenceAdapter implements GetPaginatedLoadsPort {
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
}
