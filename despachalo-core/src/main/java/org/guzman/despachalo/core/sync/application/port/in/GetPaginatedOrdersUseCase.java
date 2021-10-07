package org.guzman.despachalo.core.sync.application.port.in;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.domain.OrderState;
import org.guzman.despachalo.core.sync.domain.Order;

public interface GetPaginatedOrdersUseCase {
    Paginator<Order> execute(Filters filters, OrderState stateFilter);
}
