package org.guzman.despachalo.core.company.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.company.application.port.in.GetCenterDetailsUseCase;
import org.guzman.despachalo.core.company.application.port.out.FindCenterPort;
import org.guzman.despachalo.core.company.domain.DistributionCenter;

@UseCase
@RequiredArgsConstructor
public class GetCenterDetailsService implements GetCenterDetailsUseCase {
    private final FindCenterPort findCenterPort;

    @Override
    public DistributionCenter execute(Long centerId) {
        return findCenterPort.findCenter(centerId)
                .orElseThrow(() -> new CenterNotFoundException(centerId));
    }
}
