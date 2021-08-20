package org.guzman.despachalo.core.company.domain;

import lombok.Value;

@Value
public class Employee {
    Long id;
    String name;
    String lastname;
    String email;
}
