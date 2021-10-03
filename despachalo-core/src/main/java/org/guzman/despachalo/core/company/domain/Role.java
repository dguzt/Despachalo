package org.guzman.despachalo.core.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Role {
    private Long id;
    private String name;
    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;
}
