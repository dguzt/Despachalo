package org.guzman.despachalo.commons.errors;

import lombok.Getter;
import lombok.Value;

@Getter
public class CustomUserInputException extends RuntimeException implements UserInputException {
    private final String code = "CUSTOM_USER_INPUT_ERROR";
    private final String message;
    private final Data data;

    @Value
    static class Data {
        Long attribute1;
        String attribute2;
    }

    public CustomUserInputException(Long attribute1, String attribute2) {
        super();

        this.message = "CUSTOM_USER_INPUT_MESSAGE";
        this.data = new Data(attribute1, attribute2);
    }
}
