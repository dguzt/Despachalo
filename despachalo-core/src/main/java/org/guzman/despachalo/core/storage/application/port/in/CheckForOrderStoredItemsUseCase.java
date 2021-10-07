package org.guzman.despachalo.core.storage.application.port.in;

public interface CheckForOrderStoredItemsUseCase {
    OrderItemsChecked execute(Long orderId, Long commodityId, Long centerId);
}
