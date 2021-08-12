package org.guzman.despachalo.adapter.persistence.distribution;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.distribution.application.port.out.GetTruckPort;
import org.guzman.despachalo.core.distribution.domain.Truck;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TruckPersistenceAdapter implements GetTruckPort {
    private final TruckRepository repository;
    private final TruckMapper mapper;

    @Override
    public Optional<Truck> getTruck(Long truckId) {
        return repository
                .findById(truckId)
                .map(mapper::toTruck);
    }
}
