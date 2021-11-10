package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.ClientToRegister;

import java.util.List;

public interface RegisterClientsPort {
    void registerClients(List<ClientToRegister> toRegister);
}
