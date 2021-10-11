package org.guzman.despachalo.web.modules.sync.orders;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.sync.application.port.in.GetAllOrdersUseCase;
import org.guzman.despachalo.core.sync.domain.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.guzman.despachalo.core.sync.domain.OrderState.READY;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetAllOrdersController {
    private final GetAllOrdersUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/sync/orders/all")
    public List<Order> getShipments(@RequestParam(value = "state", defaultValue = READY) String state) {
        return useCase.execute(state);
    }
}
