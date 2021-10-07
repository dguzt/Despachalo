package org.guzman.despachalo.core.storage.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemsChecked {
    private Long orderId;
    private Long zoneAssignedId;
    private Boolean areStored;
}
