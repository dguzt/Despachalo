package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.DestinationPointToRegister;

import java.util.List;

public interface RegisterDestinationPointsPort {
    void registerDestinationPoints(List<DestinationPointToRegister> toRegister);
}
