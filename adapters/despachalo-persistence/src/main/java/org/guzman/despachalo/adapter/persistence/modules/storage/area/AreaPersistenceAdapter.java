package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.in.AreaToRegister;
import org.guzman.despachalo.core.storage.application.port.out.*;
import org.guzman.despachalo.core.storage.domain.Area;
import org.guzman.despachalo.core.storage.domain.ZoneToAssign;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class AreaPersistenceAdapter implements
        GetAreasByOrderItemsPort,
        GetPaginatedAreasPort,
        RegisterAreaPort,
        FindAreaPort,
        FindAreaForOrderPort,
        GetLeastOccupiedAreaPort,
        FindAreasByIdsPort {

    private final StoreItemRepository storeItemRepository;
    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    @Override
    public List<ZoneToAssign> getAreasByOrderIds(List<Long> orderIds) {
        return Collections.emptyList();
    }

    @Override
    public Paginator<Area> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = areaRepository.findAll(pageable);

        var data = page.getContent()
                .stream()
                .map(areaMapper::toArea)
                .collect(Collectors.toList());

        return Paginator.<Area>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }

    @Override
    public Long registerArea(AreaToRegister toRegister) {
        var row = areaMapper.toEntity(toRegister);
        return areaRepository.save(row).getId();
    }

    @Override
    public Optional<Area> findArea(Long areaId) {
        return areaRepository.findById(areaId).map(areaMapper::toArea);
    }

    @Override
    public Optional<Long> findAreaForOrder(Long orderId) {
        return storeItemRepository.findTopByItem_OrderIdOrderByStoredAtAsc(orderId)
                .map(StoredItemEntity::getAreaId);
    }

    @Override
    public Long getLeastOccupiedArea(Long centerId) {
        return areaRepository.findTopByCenterIdOrderByAvailableCapacityDesc(centerId).getId();
    }

    @Override
    public List<Area> findAreasByIds(List<Long> areaIds) {
        return areaRepository.findAllById(areaIds)
                .stream()
                .map(areaMapper::toArea)
                .collect(Collectors.toList());
    }
}
