package org.guzman.despachalo.core.distribution.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.distribution.application.port.in.GetTruckUseCase;
import org.guzman.despachalo.core.distribution.application.port.out.GetTruckPort;
import org.guzman.despachalo.core.distribution.domain.Truck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UseCase
@RequiredArgsConstructor
public class GetTruckService implements GetTruckUseCase {
    private final GetTruckPort getTruckPort;
    private final Logger logger = LoggerFactory.getLogger(GetTruckService.class);

    @Override
    public Truck execute(Long truckId) {
        logger.info("Get truck with id: {}", truckId);

        return getTruckPort
                .getTruck(truckId)
                .orElseThrow(() -> new TruckNotFoundException(truckId));
    }
}
