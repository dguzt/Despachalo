package org.guzman.despachalo.core.sync.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.application.port.in.GetAllOrdersUseCase;
import org.guzman.despachalo.core.sync.application.port.out.GetAllOrdersPort;
import org.guzman.despachalo.core.sync.domain.Order;

import java.util.Collections;
import java.util.List;

import static org.guzman.despachalo.core.sync.domain.OrderState.INCOMPLETE;
import static org.guzman.despachalo.core.sync.domain.OrderState.PROGRAMMED;

@UseCase
@RequiredArgsConstructor
public class GetAllOrdersService implements GetAllOrdersUseCase {
    private final GetAllOrdersPort ordersPort;

    @Override
    public List<Order> execute(String stateFilter) {
        switch (stateFilter) {
            case INCOMPLETE:
            case PROGRAMMED:
                return Collections.emptyList();
            default: return ordersPort.getAllReady();
        }
    }
}
