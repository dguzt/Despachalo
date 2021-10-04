package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.out.GetPaginatedShipmentsPort;
import org.guzman.despachalo.core.storage.application.port.out.GetShipmentDetailsPort;
import org.guzman.despachalo.core.storage.domain.Shipment;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ShipmentPersistenceAdapter implements GetPaginatedShipmentsPort, GetShipmentDetailsPort {
    private final CommodityRepository commodityRepository;
    private final ItemRepository itemRepository;
    private final ShipmentMapper shipmentMapper;
    private final ItemMapper itemMapper;

    @Override
    public Paginator<Shipment> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = commodityRepository.findAllByOrderByArrivalTimeDesc(pageable);

        var data = page.getContent()
                .stream()
                .map(shipmentMapper::toShipment)
                .collect(Collectors.toList());

        return Paginator.<Shipment>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }

    @Override
    public Optional<Shipment> getShipmentDetails(Long shipmentId) {
        return commodityRepository
                .findById(shipmentId)
                .map(shipmentMapper::toShipment)
                .map(s -> {
                    var items = itemRepository
                            .findAllByCommodityId(shipmentId)
                            .stream()
                            .map(itemMapper::toItem)
                            .collect(Collectors.toList());

                    s.setItems(items);
                    return s;
                });
    }
}
