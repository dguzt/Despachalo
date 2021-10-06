package org.guzman.despachalo.core.company.application.port.in;

import org.guzman.despachalo.core.company.domain.DistributionCenter;

public interface GetCenterDetailsUseCase {
    DistributionCenter execute(Long centerId);
}
