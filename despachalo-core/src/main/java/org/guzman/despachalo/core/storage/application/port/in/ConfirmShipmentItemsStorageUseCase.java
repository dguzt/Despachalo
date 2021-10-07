package org.guzman.despachalo.core.storage.application.port.in;

public interface ConfirmShipmentItemsStorageUseCase {
    void execute(Long centerId, Long areaId, Long orderId, Long shipmentId);
}
