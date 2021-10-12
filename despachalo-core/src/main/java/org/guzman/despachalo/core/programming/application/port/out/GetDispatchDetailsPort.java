package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.core.programming.domain.DispatchDetails;

import java.util.Optional;

public interface GetDispatchDetailsPort {
    Optional<DispatchDetails> getDispatchDetails(Long dispatchId);
}
