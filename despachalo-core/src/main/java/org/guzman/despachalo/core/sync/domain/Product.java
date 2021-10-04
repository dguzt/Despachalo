package org.guzman.despachalo.core.sync.domain;

import lombok.Value;

@Value
public class Product {
    Long id;
    String code;
    String description;

    Long detailsId;
    Float weight;
    Float volume;
}
