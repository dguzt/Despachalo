package org.guzman.despachalo.core.sync.load.application.port.out;

import java.util.Optional;

public interface GetNextSyncPort {
    Optional<Long> getNextSync();
}
