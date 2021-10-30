package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.GetSyncDetailsUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.GetSyncDetailsPort;
import org.guzman.despachalo.core.sync.load.domain.SyncDetails;

@UseCase
@RequiredArgsConstructor
public class GetSyncDetailsService implements GetSyncDetailsUseCase {
    private final GetSyncDetailsPort syncDetailsPort;

    @Override
    public SyncDetails execute(Long syncId) {
        return syncDetailsPort.getSyncDetails(syncId)
                .orElseThrow(() -> new SyncNotFoundException(syncId));
    }
}
