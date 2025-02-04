package org.guzman.despachalo.core.sync.order.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.order.application.port.in.GetAllOrdersUseCase;
import org.guzman.despachalo.core.sync.order.application.port.out.GetAllOrdersPort;
import org.guzman.despachalo.core.sync.order.domain.Order;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllOrdersService implements GetAllOrdersUseCase {
    private final GetAllOrdersPort ordersPort;

    @Override
    public List<Order> execute(String state) {
        return ordersPort.getAllOrdersByState(state);
    }
}
