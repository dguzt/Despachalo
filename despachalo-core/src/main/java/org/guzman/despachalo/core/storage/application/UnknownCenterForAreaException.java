package org.guzman.despachalo.core.storage.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.commons.errors.NotFoundException;

import java.io.Serializable;

@Getter
public class UnknownCenterForAreaException extends RuntimeException implements NotFoundException {
    private final String code = "STORAGE_002";
    private final String message;
    private final Data data;

    @Getter
    @AllArgsConstructor
    static class Data implements Serializable {
        Long centerId;
    }

    public UnknownCenterForAreaException(Long centerId) {
        super();
        this.message = String.format("Center with id '%d' not found for area to register", centerId);
        this.data = new Data(centerId);
    }
}
