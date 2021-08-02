package org.guzman.despachalo.commons.errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizationExceptionTest {

    @Test
    void whenThrowsCustomAuthorizationException_shouldAllowAccessErrorDataCodeAndMessage() {
        var attribute1 = 1L;
        var attribute2 = "some data";

        try {
            throw new CustomAuthorizationException(attribute1, attribute2);

        } catch (CustomAuthorizationException exception) {
            assertEquals("CUSTOM_AUTHORIZATION_ERROR", exception.getCode());
            assertEquals("CUSTOM_AUTHORIZATION_MESSAGE", exception.getMessage());

            var data = (CustomAuthorizationException.Data) exception.getData();
            Assertions.assertEquals(attribute1, data.getAttribute1());
            Assertions.assertEquals(attribute2, data.getAttribute2());
        }
    }
}
