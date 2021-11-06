package org.guzman.despachalo.core.programming.application.port.in;

import java.io.File;

public interface GetDispatchReferralGuideUseCase {
    File execute(Long dispatchId, Long programmedVehicleId, Long orderId);
}
