package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.ConfirmAllShipmentItemsStorageUseCase;
import org.guzman.despachalo.core.storage.application.port.out.CheckForShipmentCompletedPort;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ConfirmAllShipmentItemsStorageService implements ConfirmAllShipmentItemsStorageUseCase {
    private final ConfirmShipmentItemsStorageService shipmentItemsStorageService;
    private final CheckForShipmentCompletedPort shipmentCompletedPort;

    @Override
    public void execute(Long centerId, Long areaId, Long shipmentId, List<Long> orderIds) {
        orderIds.forEach(orderId -> shipmentItemsStorageService.execute(
                centerId,
                areaId,
                orderId,
                shipmentId
        ));

        shipmentCompletedPort.checkForShipmentCompleted(shipmentId);
    }
}
