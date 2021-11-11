package org.guzman.despachalo.core.sync.load.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderToInsert {
    private String code;
    private Long destinationPointId;
    private List<OrderLineToInsert> lines;

    @Getter
    @AllArgsConstructor
    public static class OrderLineToInsert {
        private Long productId;
        private Long amountRequested;
    }
}
