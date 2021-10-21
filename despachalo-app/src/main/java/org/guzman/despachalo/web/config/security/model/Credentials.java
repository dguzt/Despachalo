package org.guzman.despachalo.web.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Credentials {
    private String email;
    private String password;
}
