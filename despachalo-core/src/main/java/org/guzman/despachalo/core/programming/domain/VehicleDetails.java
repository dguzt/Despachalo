package org.guzman.despachalo.core.programming.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.sync.domain.Driver;
import org.guzman.despachalo.core.sync.domain.Truck;

import java.util.List;

@Getter
@AllArgsConstructor
public class VehicleDetails {
    private Truck vehicle;
    private Driver driver;
    private List<Long> orderIdsToDispatch;
}
