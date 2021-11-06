package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.programming.application.port.in.GetDispatchReferralGuideUseCase;
import org.guzman.despachalo.core.programming.application.port.out.GetDataForReferralGuidePort;
import org.guzman.despachalo.core.programming.application.port.out.GetReferralGuidePort;

import java.io.File;

@UseCase
@RequiredArgsConstructor
public class GetDispatchReferralGuideService implements GetDispatchReferralGuideUseCase {
    private final GetDataForReferralGuidePort dataForReferralGuidePort;
    private final GetReferralGuidePort referralGuidePort;

    @Override
    public File execute(Long dispatchId, Long programmedVehicleId, Long orderId) {
        var referralGuide = dataForReferralGuidePort.getDataForReferralGuide(dispatchId, programmedVehicleId, orderId);
        return referralGuidePort.getReferralGuide(referralGuide);
    }
}
