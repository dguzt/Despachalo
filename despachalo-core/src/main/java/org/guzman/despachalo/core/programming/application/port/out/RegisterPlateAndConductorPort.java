package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.core.programming.application.port.in.AdditionalVehicleData;

public interface RegisterPlateAndConductorPort {
    void registerPlateAndConductor(Long dispatchId, Long programmedVehicleId, AdditionalVehicleData vehicleData);
}
