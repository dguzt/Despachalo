package org.guzman.despachalo.core.user.domain;

import lombok.Value;

@Value
public class User {
    Long id;
    String name;
    String lastname;
    String email;
}
