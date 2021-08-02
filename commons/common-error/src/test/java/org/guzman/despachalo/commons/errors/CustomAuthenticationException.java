package org.guzman.despachalo.commons.errors;

import lombok.Getter;
import lombok.Value;

@Getter
public class CustomAuthenticationException extends RuntimeException implements AuthenticationException {
    private final String code = "CUSTOM_AUTHENTICATION_ERROR";
    private final String message;
    private final Object data;

    @Value @Getter
    static class Data {
        Long attribute1;
        String attribute2;
    }

    public CustomAuthenticationException(Long attribute1, String attribute2) {
        super();
        this.message = "CUSTOM_AUTHENTICATION_MESSAGE";
        this.data = new Data(attribute1, attribute2);
    }
}
