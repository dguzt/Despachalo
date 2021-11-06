package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.ItemMapper;
import org.guzman.despachalo.core.storage.domain.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface ItemStoredMapper {

    @Mapping(target = "id", source = "item.id")
    @Mapping(target = "code", source = "item.code")
    @Mapping(target = "orderId", source = "item.orderId")
    @Mapping(target = "shipmentId", source = "item.commodityId")
    @Mapping(target = "productDetailId", source = "item.productDetailId")
    @Mapping(target = "areaId", source = "areaId")
    Item toItem(StoredItemEntity entity);

    List<Item> toItems(List<StoredItemEntity> entity);
}
