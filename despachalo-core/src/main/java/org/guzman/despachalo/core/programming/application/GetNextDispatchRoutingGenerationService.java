package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.programming.application.port.in.GetNextDispatchRoutingGenerationUseCase;
import org.guzman.despachalo.core.programming.application.port.out.GetNextDispatchRoutingGenerationPort;
import org.guzman.despachalo.core.programming.domain.Dispatch;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetNextDispatchRoutingGenerationService implements GetNextDispatchRoutingGenerationUseCase {
    private final GetNextDispatchRoutingGenerationPort routingGenerationPort;

    @Override
    public Optional<Dispatch> execute() {
        return routingGenerationPort.getNextDispatchRoutingGeneration();
    }
}
