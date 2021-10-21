package org.guzman.despachalo.core.programming.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.common.domain.Location;
import org.guzman.despachalo.core.sync.vehicle.domain.Driver;
import org.guzman.despachalo.core.sync.vehicle.domain.Truck;

import java.util.List;

@Getter
@AllArgsConstructor
public class VehicleDetails {
    private Truck vehicle;
    private Driver driver;
    private List<Long> orderIdsToDispatch;
    private List<Location> routeLocations;
}
