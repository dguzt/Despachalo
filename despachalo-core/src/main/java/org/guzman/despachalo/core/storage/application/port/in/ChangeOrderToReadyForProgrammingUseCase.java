package org.guzman.despachalo.core.storage.application.port.in;

public interface ChangeOrderToReadyForProgrammingUseCase {
    void changeOrderToReadyForProgramming(Long orderId);
}
