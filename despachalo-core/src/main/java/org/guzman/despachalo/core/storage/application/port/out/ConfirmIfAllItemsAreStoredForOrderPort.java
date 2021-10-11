package org.guzman.despachalo.core.storage.application.port.out;

public interface ConfirmIfAllItemsAreStoredForOrderPort {
    Boolean confirmIfAllItemsAreStoredForOrder(Long orderId);
}
