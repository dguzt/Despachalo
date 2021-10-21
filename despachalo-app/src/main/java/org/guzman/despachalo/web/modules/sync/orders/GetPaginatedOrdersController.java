package org.guzman.despachalo.web.modules.sync.orders;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.order.application.port.in.GetPaginatedOrdersUseCase;
import org.guzman.despachalo.core.sync.order.domain.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.guzman.despachalo.core.sync.order.domain.OrderState.READY;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetPaginatedOrdersController {
    private final GetPaginatedOrdersUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/sync/orders")
    public Paginator<Order> getShipments(
            Pageable pageable,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "state", defaultValue = READY) String state) {

        var filters = Filters.builder()
                .page(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .search(search)
                .build();

        return useCase.execute(filters, state);
    }
}
