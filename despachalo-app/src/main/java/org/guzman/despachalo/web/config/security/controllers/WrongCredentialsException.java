package org.guzman.despachalo.web.config.security.controllers;

import lombok.Getter;
import org.guzman.despachalo.commons.errors.UserInputException;

@Getter
public class WrongCredentialsException extends RuntimeException implements UserInputException {
    private final String code = "SEC_002";
    private final String message = "Wrong credentials to login";
    private final Object data = null;
}
