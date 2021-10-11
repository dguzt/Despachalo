package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.core.programming.application.port.in.DispatchToRegister;
import org.guzman.despachalo.core.programming.domain.Dispatch;

public interface ProgramDispatchPort {
    Dispatch programDispatch(DispatchToRegister toRegister, Long analystId);
}
