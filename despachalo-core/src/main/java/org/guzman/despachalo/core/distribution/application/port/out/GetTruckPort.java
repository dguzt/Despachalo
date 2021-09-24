package org.guzman.despachalo.core.distribution.application.port.out;

import org.guzman.despachalo.core.distribution.domain.Truck;

import java.util.Optional;

public interface GetTruckPort {
    Optional<Truck> getTruck(Long truckId);
}
