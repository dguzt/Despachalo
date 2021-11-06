package org.guzman.despachalo.core.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {
    private Long id;
    private String code;
    private Long productDetailId;
    private Long orderId;
    private Long shipmentId;
    private Long areaId;
}
