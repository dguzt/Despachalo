package org.guzman.despachalo.core.storage.application.port.in;

public interface RegisterNewAreaUseCase {
    Long execute(AreaToRegister toRegister);
}
