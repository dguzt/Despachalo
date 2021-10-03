package org.guzman.despachalo.adapter.persistence.modules.dispatch;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.application.port.out.GetPaginatedDispatchesPort;
import org.guzman.despachalo.core.programming.domain.Dispatch;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DispatchPersistenceAdapter implements GetPaginatedDispatchesPort {
    private final DispatchRepository repository;
    private final DispatchMapper mapper;

    @Override
    public Paginator<Dispatch> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAll(pageable);

        var data = page.getContent()
                .stream()
                .map(mapper::toDispatch)
                .collect(Collectors.toList());

        return Paginator.<Dispatch>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }
}
