package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.core.storage.application.port.in.AreaToRegister;

public interface RegisterAreaPort {
    Long registerArea(AreaToRegister toRegister);
}
