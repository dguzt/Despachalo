package org.guzman.despachalo.core.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Shipment {
    private Long id;
    private Long warehouseId;
    private Long assignedCenterId;

    private String code;
    private String state;
    private String licensePlate;
    private LocalDateTime arrivalTime;

    @Setter
    private List<Item> items;
}
