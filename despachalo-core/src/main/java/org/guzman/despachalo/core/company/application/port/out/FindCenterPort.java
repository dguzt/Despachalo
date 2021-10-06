package org.guzman.despachalo.core.company.application.port.out;

import org.guzman.despachalo.core.company.domain.DistributionCenter;

import java.util.Optional;

public interface FindCenterPort {
    Optional<DistributionCenter> findCenter(Long centerId);
}
