package org.guzman.despachalo.core.sync.load.application.port.in;

import org.guzman.despachalo.core.sync.load.domain.SyncDetails;

public interface GetSyncDetailsUseCase {
    SyncDetails execute(Long syncId);
}
