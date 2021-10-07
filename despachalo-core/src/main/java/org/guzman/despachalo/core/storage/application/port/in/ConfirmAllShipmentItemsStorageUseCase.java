package org.guzman.despachalo.core.storage.application.port.in;

import java.util.List;

public interface ConfirmAllShipmentItemsStorageUseCase {
    void execute(Long centerId, Long areaId, Long shipmentId, List<Long> orderIds);
}
