package org.guzman.despachalo.commons.errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationExceptionTest {

    @Test
    void whenThrowsCustomAuthenticationException_shouldAllowAccessErrorDataCodeAndMessage() {
        var attribute1 = 1L;
        var attribute2 = "some data";

        try {
            throw new CustomAuthenticationException(attribute1, attribute2);

        } catch (CustomAuthenticationException exception) {
            assertEquals("CUSTOM_AUTHENTICATION_ERROR", exception.getCode());
            assertEquals("CUSTOM_AUTHENTICATION_MESSAGE", exception.getMessage());

            var data = (CustomAuthenticationException.Data) exception.getData();
            Assertions.assertEquals(attribute1, data.getAttribute1());
            Assertions.assertEquals(attribute2, data.getAttribute2());
        }
    }
}
