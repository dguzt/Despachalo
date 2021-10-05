package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.AssignAreasToItemsUseCase;
import org.guzman.despachalo.core.storage.application.port.out.GetZonesByOrderItemsPort;
import org.guzman.despachalo.core.storage.domain.Item;
import org.guzman.despachalo.core.storage.domain.ZoneToAssign;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class AssignAreasToItemsService implements AssignAreasToItemsUseCase {
    private final GetZonesByOrderItemsPort getZonesByOrderItemsPort;

    @Override
    public List<ZoneToAssign> execute(List<Item> items) {
        var orderIds = items.stream()
                .map(Item::getOrderId)
                .collect(Collectors.toList());

        var zones = getZonesByOrderItemsPort.getZonesByOrderIds(orderIds);

        return null;
    }
}
