package org.guzman.despachalo.core.sync.application.port.out;

import org.guzman.despachalo.core.sync.domain.Order;

import java.util.List;

public interface GetAllOrdersPort {
    List<Order> getAllReady();
}
