package org.guzman.despachalo.core.sync.order.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.order.application.port.in.GetPaginatedOrdersUseCase;
import org.guzman.despachalo.core.sync.order.application.port.out.GetPaginatedOrdersPort;
import org.guzman.despachalo.core.sync.order.domain.Order;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedOrdersService implements GetPaginatedOrdersUseCase {
    private final GetPaginatedOrdersPort ordersPort;

    @Override
    public Paginator<Order> execute(Filters filters, String state) {
        return ordersPort.getOrdersPage(filters, state);
    }
}
