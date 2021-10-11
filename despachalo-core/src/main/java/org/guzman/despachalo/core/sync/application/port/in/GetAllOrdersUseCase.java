package org.guzman.despachalo.core.sync.application.port.in;

import org.guzman.despachalo.core.sync.domain.Order;

import java.util.List;

public interface GetAllOrdersUseCase {
    List<Order> execute(String orderState);
}
