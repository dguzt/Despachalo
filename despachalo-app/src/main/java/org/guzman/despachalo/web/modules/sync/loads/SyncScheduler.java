package org.guzman.despachalo.web.modules.sync.loads;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.sync.load.application.port.in.StartSyncProcessingUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.GetNextSyncPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SyncScheduler {
    private final Logger logger = LoggerFactory.getLogger(SyncScheduler.class);
    private final GetNextSyncPort nextSyncPort;
    private final StartSyncProcessingUseCase syncProcessingUseCase;

    @Scheduled(cron = "0/15 * * ? * *")
    public void startProcessNextSync() {
        logger.info("Check for next sync to process");
        var nextSync = nextSyncPort.getNextSync();

        if (nextSync.isEmpty()) {
            logger.info("SKIPPING. No syncs to process or already one is processing");
            return;
        }

        var syncId =nextSync.get();
        logger.info("PROCESSING. Processing sync with id: {}", syncId);
        syncProcessingUseCase.execute(syncId);
        logger.info("FINISHED. Sync processed with id {}", syncId);
    }
}
