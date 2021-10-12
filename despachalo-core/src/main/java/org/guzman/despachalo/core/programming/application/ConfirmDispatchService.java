package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.programming.application.port.in.ConfirmDispatchUseCase;
import org.guzman.despachalo.core.programming.application.port.out.ConfirmDispatchPort;

@UseCase
@RequiredArgsConstructor
public class ConfirmDispatchService implements ConfirmDispatchUseCase {
    private final ConfirmDispatchPort dispatchPort;

    @Override
    public void execute(Long dispatchId) {
        dispatchPort.confirmDispatch(dispatchId);
    }
}
