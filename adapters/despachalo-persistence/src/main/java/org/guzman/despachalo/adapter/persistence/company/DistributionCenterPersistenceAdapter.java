package org.guzman.despachalo.adapter.persistence.company;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.application.port.out.RegisterDistributionCenterPort;
import org.guzman.despachalo.core.company.domain.DistributionCenter;

@PersistenceAdapter
@RequiredArgsConstructor
public class DistributionCenterPersistenceAdapter implements RegisterDistributionCenterPort {
    private final DistributionCenterRepository repository;
    private final DistributionCenterMapper mapper;

    @Override
    public DistributionCenter register(DistributionCenterToRegister toRegister) {
        var row = mapper.toEntity(toRegister);
        var saved = repository.save(row);
        return mapper.toDistributionCenter(saved);
    }
}
