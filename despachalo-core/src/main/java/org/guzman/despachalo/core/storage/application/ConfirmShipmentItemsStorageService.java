package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.ConfirmShipmentItemsStorageUseCase;
import org.guzman.despachalo.core.storage.application.port.out.StoreOrderItemsInAreaPort;

@UseCase
@RequiredArgsConstructor
public class ConfirmShipmentItemsStorageService implements ConfirmShipmentItemsStorageUseCase {
    private final CheckForOrderStoredItemsService orderStoredItemsService;
    private final StoreOrderItemsInAreaPort areaPort;

    @Override
    public void execute(Long centerId, Long areaId, Long orderId, Long shipmentId) {
        var checking = orderStoredItemsService.execute(orderId, shipmentId, centerId);
        if (checking.getAreStored()) {
            return;
        }

        areaPort.storeOrderItemsInArea(areaId, orderId, shipmentId);
    }
}
