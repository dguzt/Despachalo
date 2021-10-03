package org.guzman.despachalo.core.sync.application.port.out;

import org.guzman.despachalo.core.sync.domain.Truck;

import java.util.Optional;

public interface GetTruckPort {
    Optional<Truck> getTruck(Long truckId);
}
