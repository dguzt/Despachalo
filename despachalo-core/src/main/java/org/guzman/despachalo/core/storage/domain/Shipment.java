package org.guzman.despachalo.core.storage.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Shipment {
    Long id;
    String code;
    String state;
    String licensePlate;

    LocalDateTime arrivalTime;
    Long warehouseId;
}
