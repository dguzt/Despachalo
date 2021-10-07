package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.CheckForOrderStoredItemsUseCase;
import org.guzman.despachalo.core.storage.application.port.in.OrderItemsChecked;
import org.guzman.despachalo.core.storage.application.port.out.AreOrderItemsStoredPort;
import org.guzman.despachalo.core.storage.application.port.out.FindAreaForOrderPort;
import org.guzman.despachalo.core.storage.application.port.out.GetLeastOccupiedAreaPort;

@UseCase
@RequiredArgsConstructor
public class CheckForOrderStoredItemsService implements CheckForOrderStoredItemsUseCase {
    private final FindAreaForOrderPort areaForOrderPort;
    private final GetLeastOccupiedAreaPort leastOccupiedAreaPort;
    private final AreOrderItemsStoredPort orderItemsStoredPort;

    @Override
    public OrderItemsChecked execute(Long orderId, Long shipmentId, Long centerId) {
        var areaId = areaForOrderPort.findAreaForOrder(orderId);

        if (areaId.isEmpty()) {
            var assignedAreaId = leastOccupiedAreaPort.getLeastOccupiedArea(centerId);
            return new OrderItemsChecked(orderId, assignedAreaId, false);
        }

        var allStored = orderItemsStoredPort.areOrderItemsStored(orderId, shipmentId);
        return new OrderItemsChecked(orderId, areaId.get(), allStored);
    }
}
