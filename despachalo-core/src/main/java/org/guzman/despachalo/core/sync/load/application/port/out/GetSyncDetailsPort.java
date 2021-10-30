package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.SyncDetails;

import java.util.Optional;

public interface GetSyncDetailsPort {
    Optional<SyncDetails> getSyncDetails(Long syncId);
}
