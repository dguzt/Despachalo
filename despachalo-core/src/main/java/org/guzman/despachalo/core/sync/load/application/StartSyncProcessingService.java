package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.GetSyncDetailsUseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.StartSyncProcessingUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.GetLoadFilePort;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterSyncResultPort;
import org.guzman.despachalo.core.sync.load.application.processors.DataFileProcessor;

import java.io.IOException;

import static org.guzman.despachalo.core.sync.load.domain.LoadState.*;

@UseCase
@RequiredArgsConstructor
public class StartSyncProcessingService implements StartSyncProcessingUseCase {
    private final DataFileProcessor processor;
    private final GetSyncDetailsUseCase syncDetailsUseCase;
    private final GetLoadFilePort filePort;
    private final RegisterSyncResultPort syncResultPort;

    @Override
    public void execute(Long syncId) {
        var sync = syncDetailsUseCase.execute(syncId);
        syncResultPort.registerSyncResult(sync.getLoad().getId(), PROCESSING);
        try {
            var csv = filePort.getLoadFile(sync.getFileUrl());
            processor.process(csv, sync.getLoad().getDataType());
            syncResultPort.registerSyncResult(sync.getLoad().getId(), DONE);
        } catch (IOException e) {
            syncResultPort.registerSyncResult(sync.getLoad().getId(), FAILED);
        }
    }
}
