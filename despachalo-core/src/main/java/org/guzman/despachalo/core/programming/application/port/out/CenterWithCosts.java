package org.guzman.despachalo.core.programming.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CenterWithCosts {
    private Long centerId;
    private List<EndpointWithCost> endpointWithCosts;
}
