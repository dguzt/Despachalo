package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.core.programming.domain.Dispatch;

import java.util.Optional;

public interface GetNextDispatchRoutingGenerationPort {
    Optional<Dispatch> getNextDispatchRoutingGeneration();
}
