package org.guzman.despachalo.core.storage.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.commons.errors.NotFoundException;

import java.io.Serializable;

@Getter
public class AreaNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "STORAGE_003";
    private final String message;
    private final Data data;

    @Getter
    @AllArgsConstructor
    static class Data implements Serializable {
        Long areaId;
    }

    public AreaNotFoundException(Long areaId) {
        super();
        this.message = String.format("Area not found: %d", areaId);
        this.data = new AreaNotFoundException.Data(areaId);
    }
}
