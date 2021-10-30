package org.guzman.despachalo.core.sync.load.application;

import lombok.Getter;
import org.guzman.despachalo.commons.errors.UserInputException;

@Getter
public class FileTroubleException extends RuntimeException implements UserInputException {
    private final String code = "SYNC_002";
    private final String message;
    private final Object data = null;

    public FileTroubleException() {
        super();
        this.message = "Cannot register the data file because cannot be read";
    }
}
