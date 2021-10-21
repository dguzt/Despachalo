package org.guzman.despachalo.core.sync.order.application.port.in;

import org.guzman.despachalo.core.sync.order.domain.Order;

import java.util.List;

public interface GetAllOrdersUseCase {
    List<Order> execute(String orderState);
}
