package org.guzman.despachalo.core.sync.vehicle.application.port.out;

import org.guzman.despachalo.core.sync.vehicle.domain.Truck;

import java.util.Optional;

public interface GetTruckPort {
    Optional<Truck> getTruck(Long truckId);
}
