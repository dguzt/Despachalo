package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.ChangeOrderToReadyForProgrammingUseCase;
import org.guzman.despachalo.core.storage.application.port.out.ChangeOrderToReadyPort;
import org.guzman.despachalo.core.storage.application.port.out.ConfirmIfAllItemsAreStoredForOrderPort;

@UseCase
@RequiredArgsConstructor
public class ChangeOrderToReadyForProgrammingService implements ChangeOrderToReadyForProgrammingUseCase {
    private final ConfirmIfAllItemsAreStoredForOrderPort orderPort;
    private final ChangeOrderToReadyPort orderToReadyPort;

    @Override
    public void changeOrderToReadyForProgramming(Long orderId) {
        var allAreStored = orderPort.confirmIfAllItemsAreStoredForOrder(orderId);
        if (!allAreStored) {
            return;
        }

        orderToReadyPort.changeOrderToReady(orderId);
    }
}
