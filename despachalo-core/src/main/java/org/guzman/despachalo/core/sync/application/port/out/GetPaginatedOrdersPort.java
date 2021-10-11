package org.guzman.despachalo.core.sync.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.domain.Order;

public interface GetPaginatedOrdersPort {
    Paginator<Order> getOrdersPage(Filters filters, String state);
}
