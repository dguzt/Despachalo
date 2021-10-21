package org.guzman.despachalo.core.sync.vehicle.application;

import lombok.Getter;
import lombok.Value;
import org.guzman.despachalo.commons.errors.NotFoundException;

import java.io.Serializable;

@Getter
public class TruckNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "DIST_001";
    private final String message;
    private final Data data;

    @Value
    static class Data implements Serializable {
        Long truckId;
    }

    public TruckNotFoundException(Long truckId) {
        super();
        this.message = String.format("Truck not found: %d", truckId);
        this.data = new TruckNotFoundException.Data(truckId);
    }
}
