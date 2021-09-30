package org.guzman.despachalo.adapter.persistence.modules.sync.commodity;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.out.GetPaginatedShipmentsPort;
import org.guzman.despachalo.core.storage.domain.Shipment;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ShipmentPersistenceAdapter implements GetPaginatedShipmentsPort {
    private final CommodityRepository repository;
    private final ShipmentMapper mapper;

    @Override
    public Paginator<Shipment> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAllByOrderByArrivalTimeDesc(pageable);

        var data = page.getContent()
                .stream()
                .map(mapper::toShipment)
                .collect(Collectors.toList());

        return Paginator.<Shipment>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }
}
