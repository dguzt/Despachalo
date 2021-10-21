package org.guzman.despachalo.core.sync.order.application.port.out;

import org.guzman.despachalo.core.sync.order.domain.Order;

import java.util.List;

public interface GetAllOrdersPort {
    List<Order> getAllOrdersByState(String state);
}
