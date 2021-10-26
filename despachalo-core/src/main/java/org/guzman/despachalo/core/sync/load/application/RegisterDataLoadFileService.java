package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.load.application.port.in.RegisterDataLoadFileUseCase;

import java.io.File;

@UseCase
@RequiredArgsConstructor
public class RegisterDataLoadFileService implements RegisterDataLoadFileUseCase {
    @Override
    public void execute(Long responsibleId, File csv) {

    }
}
