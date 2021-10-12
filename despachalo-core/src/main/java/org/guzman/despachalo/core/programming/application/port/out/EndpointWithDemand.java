package org.guzman.despachalo.core.programming.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EndpointWithDemand {
    private Long orderId;
    private Long endpointId;
    private Integer demand;
}
