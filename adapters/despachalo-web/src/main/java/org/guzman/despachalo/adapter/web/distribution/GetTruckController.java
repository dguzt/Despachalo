package org.guzman.despachalo.adapter.web.distribution;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.distribution.application.TruckNotFoundException;
import org.guzman.despachalo.core.distribution.application.port.in.GetTruckUseCase;
import org.guzman.despachalo.core.distribution.domain.Truck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class GetTruckController {
    private final GetTruckUseCase getTruckUseCase;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TruckNotFoundException.class)
    public ResponseEntity<TruckNotFoundException> handleBookNotFound(TruckNotFoundException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/trucks/{truckId}")
    public Truck getTruck(@PathVariable Long truckId) {
        return getTruckUseCase.execute(truckId);
    }
}
