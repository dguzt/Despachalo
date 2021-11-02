package org.guzman.despachalo.core.sync.load.application.port.in;

import java.io.File;

public interface RegisterDataLoadFileUseCase {
    void execute(Long responsibleId, File csv, String originalName);
}
