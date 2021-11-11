package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import org.guzman.despachalo.core.storage.domain.Item;
import org.guzman.despachalo.core.sync.load.domain.CommodityToRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "shipmentId", source = "commodityId")
    @Mapping(target = "productDetailId", source = "productDetailId")
    @Mapping(target = "areaId", ignore = true)
    Item toItem(ItemEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "productDetailId", source = "productId")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "commodity", ignore = true)
    @Mapping(target = "commodityId", ignore = true)
    @Mapping(target = "returnOrder", ignore = true)
    @Mapping(target = "returnOrderId", ignore = true)
    ItemEntity toEntity(CommodityToRegister.ItemToRegister toRegister);
}
