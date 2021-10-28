package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.RegisterDataLoadFileUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterLoadForSyncPort;
import org.guzman.despachalo.core.sync.load.application.port.out.StoreLoadFilePort;
import org.guzman.despachalo.core.sync.load.domain.LoadState;
import org.guzman.despachalo.core.sync.load.domain.LoadToRegister;

import java.io.File;
import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
public class RegisterDataLoadFileService implements RegisterDataLoadFileUseCase {
    private final DataFileIdentifier dataFileIdentifier;
    private final StoreLoadFilePort filePort;
    private final RegisterLoadForSyncPort syncPort;

    @Override
    public void execute(Long responsibleId, File csv) {
        var type = dataFileIdentifier.identify(csv);

        var url = filePort.storeLoadFile(csv);
        var now = LocalDateTime.now();

        var toRegister = new LoadToRegister(responsibleId, LoadState.PENDING, type, url, now);
        syncPort.registerLoadForSync(toRegister);
    }
}
