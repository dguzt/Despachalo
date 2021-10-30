package org.guzman.despachalo.core.sync.load.application.port.in;

public interface StartSyncProcessingUseCase {
    void execute(Long syncId);
}
