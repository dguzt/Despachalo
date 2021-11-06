package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.core.programming.domain.ReferralGuideData;

public interface GetDataForReferralGuidePort {
    ReferralGuideData getDataForReferralGuide(Long dispatchId, Long programmedVehicleId, Long orderId);
}
