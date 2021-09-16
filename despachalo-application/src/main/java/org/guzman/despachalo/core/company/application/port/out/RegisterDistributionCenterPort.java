package org.guzman.despachalo.core.company.application.port.out;

import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.domain.DistributionCenter;

public interface RegisterDistributionCenterPort {
    DistributionCenter register(DistributionCenterToRegister toRegister);
}
