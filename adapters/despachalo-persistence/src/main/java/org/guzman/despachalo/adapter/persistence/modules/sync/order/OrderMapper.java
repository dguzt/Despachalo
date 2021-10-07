package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import org.guzman.despachalo.adapter.persistence.modules.sync.client.ClientMapper;
import org.guzman.despachalo.core.sync.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "client", source = "endPoint.client")
    Order toOrder(OrderEntity entity);
}
