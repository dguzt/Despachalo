package org.guzman.despachalo.web.config.security.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
