package org.guzman.despachalo.core.programming.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EndpointWithCost {
    private Long endpointId;
    private Double cost;
}
