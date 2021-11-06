package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.programming.application.port.in.AdditionalVehicleData;
import org.guzman.despachalo.core.programming.application.port.in.RegisterPlateAndConductorUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterPlateAndConductorController {
    private final RegisterPlateAndConductorUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/programming/dispatches/{dispatchId}/vehicles/{vehicleId}/details")
    public void registerPlateAndConductor(
            @PathVariable("dispatchId") Long dispatchId,
            @PathVariable("vehicleId") Long programmedVehicleId,
            @RequestBody AdditionalVehicleData vehicleData) {
        useCase.execute(dispatchId, programmedVehicleId, vehicleData);
    }
}
