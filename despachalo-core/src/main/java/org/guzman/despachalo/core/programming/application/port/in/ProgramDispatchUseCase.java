package org.guzman.despachalo.core.programming.application.port.in;

import org.guzman.despachalo.core.programming.domain.Dispatch;

public interface ProgramDispatchUseCase {
    Dispatch execute(DispatchToRegister toRegister, Long analystId);
}
