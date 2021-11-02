package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.GetSyncDetailsUseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.StartSyncProcessingUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.GetLoadFilePort;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterSyncResultPort;
import org.guzman.despachalo.core.sync.load.application.processors.DataFileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.guzman.despachalo.core.sync.load.domain.LoadState.*;

@UseCase
@RequiredArgsConstructor
public class StartSyncProcessingService implements StartSyncProcessingUseCase {
    private final Logger logger = LoggerFactory.getLogger(StartSyncProcessingService.class);
    private final DataFileProcessor processor;
    private final GetSyncDetailsUseCase syncDetailsUseCase;
    private final GetLoadFilePort filePort;
    private final RegisterSyncResultPort syncResultPort;

    @Override
    public void execute(Long syncId) {
        var sync = syncDetailsUseCase.execute(syncId);
        logger.info("[SYNC][PROCESSING] Sync processing with id: {}", syncId);
        syncResultPort.registerSyncResult(sync.getLoad().getId(), PROCESSING);
        try {
            var csv = filePort.getLoadFile(sync.getFileUrl());
            processor.process(csv, sync.getLoad().getDataType());
            syncResultPort.registerSyncResult(sync.getLoad().getId(), DONE);
            logger.info("[SYNC][DONE] Sync finished successfully");

        } catch (IOException e) {
            logger.error("[SYNC][FAILED]. Cannot sync because of IO Error: {}", e.getMessage());
            syncResultPort.registerSyncResult(sync.getLoad().getId(), FAILED);
        }
    }
}
