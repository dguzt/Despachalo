package org.guzman.despachalo.core.programming.application.port.in;

public interface RegisterPlateAndConductorUseCase {
    void execute(Long dispatchId, Long programmedVehicleId, AdditionalVehicleData vehicleData);
}
