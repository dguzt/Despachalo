package org.guzman.despachalo.commons.errors;

import lombok.Getter;
import lombok.Value;

@Getter
public class CustomAuthorizationException extends RuntimeException implements AuthorizationException {
    private final String code = "CUSTOM_AUTHORIZATION_ERROR";
    private final String message;
    private final Object data;

    @Value @Getter
    static class Data {
        Long attribute1;
        String attribute2;
    }

    public CustomAuthorizationException(Long attribute1, String attribute2) {
        super();
        this.message = "CUSTOM_AUTHORIZATION_MESSAGE";
        this.data = new Data(attribute1, attribute2);
    }
}
