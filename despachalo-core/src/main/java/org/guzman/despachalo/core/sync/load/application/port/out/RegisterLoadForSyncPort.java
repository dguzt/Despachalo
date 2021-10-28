package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.LoadToRegister;

public interface RegisterLoadForSyncPort {
    void registerLoadForSync(LoadToRegister toRegister);
}
