package org.guzman.despachalo.core.company.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Role {
    Long id;
    String name;
    LocalDateTime registeredAt;
    LocalDateTime updatedAt;
}
