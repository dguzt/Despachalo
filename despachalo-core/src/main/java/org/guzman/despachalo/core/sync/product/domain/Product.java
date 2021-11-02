package org.guzman.despachalo.core.sync.product.domain;

import lombok.Value;

@Value
public class Product {
    Long id;
    String code;
    String description;

    Long detailsId;
    Double weight;
    Double volume;
}
