package org.guzman.despachalo.core.storage.application.port.out;

public interface StoreOrderItemsInAreaPort {
    void storeOrderItemsInArea(Long areaId, Long orderId, Long shipmentId);
}
