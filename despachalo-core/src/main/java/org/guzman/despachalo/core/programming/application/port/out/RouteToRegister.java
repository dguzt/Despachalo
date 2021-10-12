package org.guzman.despachalo.core.programming.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RouteToRegister {
    private Long orderId;
    private Long dispatchId;
    private Integer deliveryOrder;
}
