package org.guzman.despachalo.core.storage.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.commons.errors.NotFoundException;

import java.io.Serializable;

@Getter
public class ShipmentNotFoundException extends RuntimeException implements NotFoundException {
    private final String code = "STORAGE_001";
    private final String message;
    private final Data data;

    @Getter
    @AllArgsConstructor
    static class Data implements Serializable {
        Long shipmentId;
    }

    public ShipmentNotFoundException(Long shipmentId) {
        super();
        this.message = String.format("Shipment not found: %d", shipmentId);
        this.data = new ShipmentNotFoundException.Data(shipmentId);
    }
}
