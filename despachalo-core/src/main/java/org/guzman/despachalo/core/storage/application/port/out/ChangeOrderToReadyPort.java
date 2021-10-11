package org.guzman.despachalo.core.storage.application.port.out;

public interface ChangeOrderToReadyPort {
    void changeOrderToReady(Long orderId);
}
