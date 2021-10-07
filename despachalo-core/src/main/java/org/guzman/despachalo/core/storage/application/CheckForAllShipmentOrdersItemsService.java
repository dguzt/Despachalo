package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.CheckForAllShipmentOrdersItemsUseCase;
import org.guzman.despachalo.core.storage.application.port.in.OrderItemsChecked;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CheckForAllShipmentOrdersItemsService implements CheckForAllShipmentOrdersItemsUseCase {
    private final CheckForOrderStoredItemsService service;

    @Override
    public List<OrderItemsChecked> execute(List<Long> orderIds, Long shipmentId, Long centerId) {
        return orderIds.stream()
                .map(orderId -> service.execute(orderId, shipmentId, centerId))
                .collect(Collectors.toList());
    }
}
