package org.guzman.despachalo.core.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Area {
    private Long id;
    private String description;
    private Integer totalCapacity;
    private Integer availableCapacity;
}
