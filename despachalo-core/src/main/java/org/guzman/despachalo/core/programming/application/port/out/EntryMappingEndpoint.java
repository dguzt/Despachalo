package org.guzman.despachalo.core.programming.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EntryMappingEndpoint {
    private Integer position;
    private Long orderId;
    private Double demand;
}
