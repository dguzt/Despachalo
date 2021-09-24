package org.guzman.despachalo.core.distribution.application.port.in;

import org.guzman.despachalo.core.distribution.domain.Truck;

public interface GetTruckUseCase {
    Truck execute(Long truckId);
}
