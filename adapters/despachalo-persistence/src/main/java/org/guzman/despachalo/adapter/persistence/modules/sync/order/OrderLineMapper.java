package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import org.guzman.despachalo.adapter.persistence.modules.sync.client.ClientMapper;
import org.guzman.despachalo.core.sync.load.domain.OrderToInsert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface OrderLineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "productDetail", ignore = true)
    @Mapping(target = "productDetailId", source = "productId")
    @Mapping(target = "requestedAmount", source = "amountRequested")
    @Mapping(target = "toSendAmount", source = "amountRequested")
    @Mapping(target = "storedAmount", ignore = true)
    @Mapping(target = "sentAmount", ignore = true)
    OrderLineEntity toEntity(OrderToInsert.OrderLineToInsert lineToInsert);
}
