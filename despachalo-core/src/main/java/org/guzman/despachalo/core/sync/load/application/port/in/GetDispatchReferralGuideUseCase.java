package org.guzman.despachalo.core.sync.load.application.port.in;

import java.io.File;

public interface GetDispatchReferralGuideUseCase {
    File execute(Long dispatchId);
}
