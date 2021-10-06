package org.guzman.despachalo.core.company.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.commons.errors.NotFoundException;

import java.io.Serializable;

@Getter
public class CenterNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "COMPANY_001";
    private final String message;
    private final Data data;

    @Getter
    @AllArgsConstructor
    static class Data implements Serializable {
        Long centerId;
    }

    public CenterNotFoundException(Long centerId) {
        super();
        this.message = String.format("Center not found: %d", centerId);
        this.data = new CenterNotFoundException.Data(centerId);
    }
}
