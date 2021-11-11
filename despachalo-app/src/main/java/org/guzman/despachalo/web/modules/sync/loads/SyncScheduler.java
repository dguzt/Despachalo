package org.guzman.despachalo.web.modules.sync.loads;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.sync.load.application.port.in.RegisterDataLoadFileUseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.StartSyncProcessingUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.GetFilenamesInSyncPort;
import org.guzman.despachalo.core.sync.load.application.port.out.GetNextSyncPort;
import org.guzman.despachalo.web.config.SyncVars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SyncScheduler {
    private final Logger logger = LoggerFactory.getLogger(SyncScheduler.class);
    private final GetNextSyncPort nextSyncPort;
    private final StartSyncProcessingUseCase syncProcessingUseCase;
    private final RegisterDataLoadFileUseCase dataLoadFileUseCase;
    private final GetFilenamesInSyncPort filenamesInSyncPort;
    private final SyncVars syncVars;

    @Scheduled(cron = "0/5 * * ? * *")
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

    @Scheduled(cron = "0/5 * * ? * *")
    public void checkFilesAndSync() throws IOException {
        var processed = filenamesInSyncPort.getFilenamesInSync();
        var files = listFilesUsingFilesList(syncVars.getDirectoryPath());
        files.forEach(file -> {
            if (processed.contains(file)) {
                return;
            }

            logger.info("[SYNC] Register file to sync: {}", file);
            var csv = new File(syncVars.getDirectoryPath() + "/" + file);
            dataLoadFileUseCase.execute(syncVars.getResponsibleId(), csv, file);
        });
    }

    public List<String> listFilesUsingFilesList(String dir) throws IOException {
        try (var stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(file -> file.contains(".csv"))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (RuntimeException ex) {
            return Collections.emptyList();
        }
    }
}
