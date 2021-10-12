package org.guzman.despachalo.core.programming.application;

import lombok.Getter;
import lombok.Value;
import org.guzman.despachalo.commons.errors.NotFoundException;

import java.io.Serializable;

@Getter
public class DispatchNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "PROG_001";
    private final String message;
    private final Data data;

    @Value
    static class Data implements Serializable {
        Long dispatchId;
    }

    public DispatchNotFoundException(Long dispatchId) {
        super();
        this.message = String.format("Dispatch not found: %d", dispatchId);
        this.data = new DispatchNotFoundException.Data(dispatchId);
    }
}
