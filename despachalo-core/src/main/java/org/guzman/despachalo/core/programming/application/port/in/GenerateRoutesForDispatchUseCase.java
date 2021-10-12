package org.guzman.despachalo.core.programming.application.port.in;

public interface GenerateRoutesForDispatchUseCase {
    void execute(Long dispatchId, Double commonCapacity);
}
