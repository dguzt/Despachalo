package org.guzman.despachalo.web.config.security.controllers;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
