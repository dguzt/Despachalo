package org.guzman.despachalo.core.programming.application.port.in;

import org.guzman.despachalo.core.programming.domain.DispatchDetails;

public interface GetDispatchDetailsUseCase {
    DispatchDetails execute(Long dispatchId);
}
