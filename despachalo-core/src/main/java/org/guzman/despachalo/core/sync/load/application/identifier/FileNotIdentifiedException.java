package org.guzman.despachalo.core.sync.load.application.identifier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.commons.errors.UserInputException;

import java.io.Serializable;

@Getter
public class FileNotIdentifiedException extends RuntimeException implements UserInputException {
    private final String code = "SYNC_003";
    private final String message;
    private final Data data;

    @Getter
    @AllArgsConstructor
    static class Data implements Serializable {
        String filename;
    }

    public FileNotIdentifiedException(String filename) {
        super();
        this.message = "Csv file not identified using its column names";
        this.data = new FileNotIdentifiedException.Data(filename);
    }
}
