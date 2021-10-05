package org.guzman.despachalo.core.storage.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AreaToRegister {
    private String description;
    private Integer totalCapacity;
    private Long centerId;
}
