package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.OrderToInsert;

import java.util.List;

public interface RegisterOrdersPort {
    void registerOrders(List<OrderToInsert> toInsert);
}
