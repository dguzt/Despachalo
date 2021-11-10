package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.OriginPointToRegister;

import java.util.List;

public interface RegisterOriginPointsPort {
    void registerOriginPoints(List<OriginPointToRegister> toRegister);
}
