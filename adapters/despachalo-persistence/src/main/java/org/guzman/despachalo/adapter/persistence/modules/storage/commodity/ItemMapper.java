package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import org.guzman.despachalo.core.storage.domain.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "shipmentId", source = "commodityId")
    @Mapping(target = "productDetailId", source = "productDetailId")
    Item toItem(ItemEntity entity);
}
