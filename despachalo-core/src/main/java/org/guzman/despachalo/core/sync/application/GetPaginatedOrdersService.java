package org.guzman.despachalo.core.sync.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.application.port.in.GetPaginatedOrdersUseCase;
import org.guzman.despachalo.core.sync.application.port.out.GetPaginatedOrdersPort;
import org.guzman.despachalo.core.sync.domain.OrderState;
import org.guzman.despachalo.core.sync.domain.Order;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedOrdersService implements GetPaginatedOrdersUseCase {
    private final GetPaginatedOrdersPort getPaginatedOrdersPort;

    @Override
    public Paginator<Order> execute(Filters filters, OrderState stateFilter) {
        switch (stateFilter) {
            case INCOMPLETE: return getPaginatedOrdersPort.getIncompletePage(filters);
            case PROGRAMMED: return getPaginatedOrdersPort.getProgrammedPage(filters);
            default: return getPaginatedOrdersPort.getReadyPage(filters);
        }
    }
}
