package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.programming.application.port.in.DispatchToRegister;
import org.guzman.despachalo.core.programming.application.port.in.ProgramDispatchUseCase;
import org.guzman.despachalo.core.programming.application.port.out.ProgramDispatchPort;
import org.guzman.despachalo.core.programming.domain.Dispatch;

@UseCase
@RequiredArgsConstructor
public class ProgramDispatchService implements ProgramDispatchUseCase {
    private final ProgramDispatchPort dispatchPort;

    @Override
    public Dispatch execute(DispatchToRegister toRegister, Long analystId) {
        return dispatchPort.programDispatch(toRegister, analystId);
    }
}
