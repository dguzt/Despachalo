package org.guzman.despachalo.core.sync.load.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommodityToRegister {
    private String code;
    private String plate;
    private String state;
    private String deliveryGeocode;
    private LocalDateTime arrivalDate;
    private List<ItemToRegister> items;

    private Long originPointId;
    private Long centerId;

    @Getter
    @AllArgsConstructor
    public static class ItemToRegister {
        private String code;
        private Long productId;
        private Long orderId;
    }
}
