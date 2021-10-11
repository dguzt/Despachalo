package org.guzman.despachalo.core.sync.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.application.port.in.GetPaginatedOrdersUseCase;
import org.guzman.despachalo.core.sync.application.port.out.GetPaginatedOrdersPort;
import org.guzman.despachalo.core.sync.domain.Order;

import static org.guzman.despachalo.core.sync.domain.OrderState.INCOMPLETE;
import static org.guzman.despachalo.core.sync.domain.OrderState.PROGRAMMED;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedOrdersService implements GetPaginatedOrdersUseCase {
    private final GetPaginatedOrdersPort ordersPort;

    @Override
    public Paginator<Order> execute(Filters filters, String state) {
        return ordersPort.getOrdersPage(filters, state);
    }
}
