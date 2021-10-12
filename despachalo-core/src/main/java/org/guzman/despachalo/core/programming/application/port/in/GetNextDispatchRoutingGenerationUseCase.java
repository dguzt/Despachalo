package org.guzman.despachalo.core.programming.application.port.in;

import org.guzman.despachalo.core.programming.domain.Dispatch;

import java.util.Optional;

public interface GetNextDispatchRoutingGenerationUseCase {
    Optional<Dispatch> execute();
}
