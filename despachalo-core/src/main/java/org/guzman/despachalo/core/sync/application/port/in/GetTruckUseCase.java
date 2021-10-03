package org.guzman.despachalo.core.sync.application.port.in;

import org.guzman.despachalo.core.sync.domain.Truck;

public interface GetTruckUseCase {
    Truck execute(Long truckId);
}
