package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.core.programming.domain.ReferralGuideData;

import java.io.File;

public interface GetReferralGuidePort {
    File getReferralGuide(ReferralGuideData data);
}
