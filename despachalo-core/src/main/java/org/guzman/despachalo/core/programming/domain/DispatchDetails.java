package org.guzman.despachalo.core.programming.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.guzman.despachalo.core.sync.domain.Order;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class DispatchDetails {
    private Dispatch dispatch;
    private Integer neededVehicles;
    private String routeRequestState;

    private List<Order> orders;
    private List<VehicleDetails> vehicleDetails;
}
