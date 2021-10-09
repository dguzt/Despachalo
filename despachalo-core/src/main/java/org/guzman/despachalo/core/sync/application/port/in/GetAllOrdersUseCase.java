package org.guzman.despachalo.core.sync.application.port.in;

import org.guzman.despachalo.core.sync.domain.Order;
import org.guzman.despachalo.core.sync.domain.OrderState;

import java.util.List;

public interface GetAllOrdersUseCase {
    List<Order> execute(OrderState orderState);
}
