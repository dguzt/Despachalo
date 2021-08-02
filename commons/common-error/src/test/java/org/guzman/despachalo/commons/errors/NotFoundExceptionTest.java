package org.guzman.despachalo.commons.errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundExceptionTest {

    @Test
    void whenThrowsCustomNotFoundException_shouldAllowAccessErrorDataCodeAndMessage() {
        var attribute1 = 1L;
        var attribute2 = "some data";

        try {
            throw new CustomNotFoundException(attribute1, attribute2);

        } catch (CustomNotFoundException exception) {
            assertEquals("CUSTOM_NOT_FOUND_ERROR", exception.getCode());
            assertEquals("CUSTOM_NOT_FOUND_MESSAGE", exception.getMessage());

            var data = (CustomNotFoundException.Data) exception.getData();
            Assertions.assertEquals(attribute1, data.getAttribute1());
            Assertions.assertEquals(attribute2, data.getAttribute2());
        }
    }
}
