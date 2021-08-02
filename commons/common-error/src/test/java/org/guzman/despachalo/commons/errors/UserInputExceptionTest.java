package org.guzman.despachalo.commons.errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserInputExceptionTest {

    @Test
    void whenThrowsCustomUserInputException_shouldAllowAccessErrorDataCodeAndMessage() {
        var attribute1 = 1L;
        var attribute2 = "some data";

        try {
            throw new CustomUserInputException(attribute1, attribute2);

        } catch (CustomUserInputException exception) {
            assertEquals("CUSTOM_USER_INPUT_ERROR", exception.getCode());
            assertEquals("CUSTOM_USER_INPUT_MESSAGE", exception.getMessage());

            var data = (CustomUserInputException.Data) exception.getData();
            Assertions.assertEquals(attribute1, data.getAttribute1());
            Assertions.assertEquals(attribute2, data.getAttribute2());
        }
    }
}
