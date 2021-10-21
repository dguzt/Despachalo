package org.guzman.despachalo.core.sync.order.application.port.in;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.order.domain.Order;

public interface GetPaginatedOrdersUseCase {
    Paginator<Order> execute(Filters filters, String stateFilter);
}
