package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import org.guzman.despachalo.adapter.persistence.modules.sync.client.ClientMapper;
import org.guzman.despachalo.core.sync.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "client", source = "endPoint.client")
    @Mapping(target = "requestedUnits", ignore = true)
    @Mapping(target = "sentUnits", ignore = true)
    @Mapping(target = "storedUnits", ignore = true)
    @Mapping(target = "toSendUnits", ignore = true)
    Order toOrder(OrderEntity entity);
}
