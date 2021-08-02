package org.guzman.despachalo.commons.errors;

import lombok.Getter;
import lombok.Value;

@Getter
public class CustomNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "CUSTOM_NOT_FOUND_ERROR";
    private final String message;
    private final Object data;

    @Value
    static class Data {
        Long attribute1;
        String attribute2;
    }

    public CustomNotFoundException(Long attribute1, String attribute2) {
        super();
        this.message = "CUSTOM_NOT_FOUND_MESSAGE";
        this.data = new Data(attribute1, attribute2);
    }
}
