package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.out.GetPaginatedAreasPort;
import org.guzman.despachalo.core.storage.application.port.out.GetZonesByOrderItemsPort;
import org.guzman.despachalo.core.storage.domain.Area;
import org.guzman.despachalo.core.storage.domain.ZoneToAssign;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class AreaPersistenceAdapter implements GetZonesByOrderItemsPort, GetPaginatedAreasPort {
    private final AreaRepository repository;
    private final AreaMapper mapper;

    @Override
    public List<ZoneToAssign> getZonesByOrderIds(List<Long> orderIds) {
        return Collections.emptyList();
    }

    @Override
    public Paginator<Area> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAll(pageable);

        var data = page.getContent()
                .stream()
                .map(mapper::toArea)
                .collect(Collectors.toList());

        return Paginator.<Area>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }
}
