package org.guzman.despachalo.core.distribution.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.distribution.application.port.in.GetTruckUseCase;
import org.guzman.despachalo.core.distribution.application.port.out.GetTruckPort;
import org.guzman.despachalo.core.distribution.domain.Truck;

@UseCase
@RequiredArgsConstructor
public class GetTruckService implements GetTruckUseCase {
    private final GetTruckPort getTruckPort;

    @Override
    public Truck execute(Long truckId) {
        return getTruckPort
                .getTruck(truckId)
                .orElseThrow(() -> new TruckNotFoundException(truckId));
    }
}
