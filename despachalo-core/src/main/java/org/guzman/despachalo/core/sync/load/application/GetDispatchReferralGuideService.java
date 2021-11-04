package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.GetDispatchReferralGuideUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.GetReferralGuidePort;

import java.io.File;

@UseCase
@RequiredArgsConstructor
public class GetDispatchReferralGuideService implements GetDispatchReferralGuideUseCase {
    private final GetReferralGuidePort referralGuidePort;

    @Override
    public File execute(Long dispatchId) {
        return referralGuidePort.getReferralGuide();
    }
}
