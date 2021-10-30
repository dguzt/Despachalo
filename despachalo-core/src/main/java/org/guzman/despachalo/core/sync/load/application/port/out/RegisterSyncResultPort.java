package org.guzman.despachalo.core.sync.load.application.port.out;

public interface RegisterSyncResultPort {
    void registerSyncResult(Long syncId, String state);
}
