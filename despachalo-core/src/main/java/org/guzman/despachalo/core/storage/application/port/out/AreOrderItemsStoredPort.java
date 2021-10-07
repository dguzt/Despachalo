package org.guzman.despachalo.core.storage.application.port.out;

public interface AreOrderItemsStoredPort {
    Boolean areOrderItemsStored(Long orderId, Long commodityId);
}
