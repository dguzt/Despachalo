package org.guzman.despachalo.core.company.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.application.port.in.RegisterDistributionCenterUseCase;
import org.guzman.despachalo.core.company.application.port.out.RegisterDistributionCenterPort;
import org.guzman.despachalo.core.company.domain.DistributionCenter;

@UseCase
@RequiredArgsConstructor
public class RegisterDistributionCenterService implements RegisterDistributionCenterUseCase {
    private final RegisterDistributionCenterPort registerDistributionCenterPort;

    @Override
    public DistributionCenter execute(DistributionCenterToRegister toRegister) {
        return registerDistributionCenterPort.register(toRegister);
    }
}
