package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.application.processors.DataFileProcessor;

public interface RegisterSyncResultPort {
    void registerSyncResult(Long syncId, String state, DataFileProcessor.Results results);
    void registerSyncResult(Long syncId, String state);
}
