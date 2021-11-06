package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.programming.application.port.in.AdditionalVehicleData;
import org.guzman.despachalo.core.programming.application.port.in.RegisterPlateAndConductorUseCase;
import org.guzman.despachalo.core.programming.application.port.out.RegisterPlateAndConductorPort;

@UseCase
@RequiredArgsConstructor
public class RegisterPlateAndConductorService implements RegisterPlateAndConductorUseCase {
    private final RegisterPlateAndConductorPort plateAndConductorPort;

    @Override
    public void execute(Long dispatchId, Long programmedVehicleId, AdditionalVehicleData vehicleData) {
        plateAndConductorPort.registerPlateAndConductor(dispatchId, programmedVehicleId, vehicleData);
    }
}
