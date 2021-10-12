package org.guzman.despachalo.core.sync.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Driver {
    private Long id;
    private String documentType;
    private String documentNumber;
}
