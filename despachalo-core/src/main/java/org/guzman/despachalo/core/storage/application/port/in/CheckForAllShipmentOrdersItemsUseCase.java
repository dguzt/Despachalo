package org.guzman.despachalo.core.storage.application.port.in;

import java.util.List;

public interface CheckForAllShipmentOrdersItemsUseCase {
    List<OrderItemsChecked> execute(List<Long> orderIds, Long shipmentId, Long centerId);
}
