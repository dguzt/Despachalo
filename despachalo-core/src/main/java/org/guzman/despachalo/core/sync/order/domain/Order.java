package org.guzman.despachalo.core.sync.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Order {
    private Long id;
    private Client client;
    private String state;

    @Setter
    private Integer requestedUnits;
    @Setter
    private Integer sentUnits;
    @Setter
    private Integer toSendUnits;
    @Setter
    private Integer storedUnits;
}
