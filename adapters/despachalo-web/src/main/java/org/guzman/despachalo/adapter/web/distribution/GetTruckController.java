package org.guzman.despachalo.adapter.web.distribution;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.distribution.application.port.in.GetTruckUseCase;
import org.guzman.despachalo.core.distribution.domain.Truck;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetTruckController {
    private final GetTruckUseCase getTruckUseCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/trucks/{truckId}")
    public Truck getTruck(@PathVariable Long truckId) {
        return getTruckUseCase.execute(truckId);
    }
}
