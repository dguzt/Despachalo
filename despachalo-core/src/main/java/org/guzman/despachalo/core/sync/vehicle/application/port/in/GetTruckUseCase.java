package org.guzman.despachalo.core.sync.vehicle.application.port.in;

import org.guzman.despachalo.core.sync.vehicle.domain.Truck;

public interface GetTruckUseCase {
    Truck execute(Long truckId);
}
