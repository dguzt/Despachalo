package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.load.application.identifier.DataFileIdentifier;
import org.guzman.despachalo.core.sync.load.application.port.in.RegisterDataLoadFileUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterLoadForSyncPort;
import org.guzman.despachalo.core.sync.load.application.port.out.StoreLoadFilePort;
import org.guzman.despachalo.core.sync.load.domain.LoadState;
import org.guzman.despachalo.core.sync.load.domain.LoadToRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class RegisterDataLoadFileService implements RegisterDataLoadFileUseCase {
    private final Logger logger = LoggerFactory.getLogger(RegisterDataLoadFileService.class);
    private final DataFileIdentifier dataFileIdentifier;
    private final StoreLoadFilePort filePort;
    private final RegisterLoadForSyncPort syncPort;

    @Override
    public void execute(Long responsibleId, File csv) {
        try {
            var type = dataFileIdentifier.identify(csv);

            var url = filePort.storeLoadFile(csv);
            var now = LocalDateTime.now();

            var toRegister = new LoadToRegister(responsibleId, LoadState.PENDING, type, url, now);
            syncPort.registerLoadForSync(toRegister);
        } catch(IOException e) {
            logger.error("Cannot read csv file given to register when identifying its data type.");
            logger.error(e.getMessage());
            throw new FileTroubleException();
        }
    }
}
