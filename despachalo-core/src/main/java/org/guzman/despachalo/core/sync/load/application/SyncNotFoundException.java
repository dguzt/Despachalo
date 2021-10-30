package org.guzman.despachalo.core.sync.load.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.commons.errors.NotFoundException;

import java.io.Serializable;

@Getter
public class SyncNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "SYNC_001";
    private final String message;
    private final Data data;

    @Getter
    @AllArgsConstructor
    static class Data implements Serializable {
        Long syncId;
    }

    public SyncNotFoundException(Long syncId) {
        super();
        this.message = String.format("Sync not found: %d", syncId);
        this.data = new SyncNotFoundException.Data(syncId);
    }
}
