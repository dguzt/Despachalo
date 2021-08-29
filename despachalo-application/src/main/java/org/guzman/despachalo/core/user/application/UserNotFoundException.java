package org.guzman.despachalo.core.user.application;

import lombok.Getter;
import org.guzman.despachalo.commons.errors.NotFoundException;

@Getter
public class UserNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "COM_001";
    private final String message;
    private final Object data = null;

    public UserNotFoundException(String email) {
        super();
        this.message = String.format("User not found with email: %s", email);
    }

    public UserNotFoundException(Long id) {
        super();
        this.message = String.format("User not found with id: %d", id);
    }
}
