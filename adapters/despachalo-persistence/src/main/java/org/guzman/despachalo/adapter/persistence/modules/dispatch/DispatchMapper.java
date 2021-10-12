package org.guzman.despachalo.adapter.persistence.modules.dispatch;

import org.guzman.despachalo.core.programming.application.port.in.DispatchToRegister;
import org.guzman.despachalo.core.programming.domain.Dispatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DispatchMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "analystId", source = "analystId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "departureTime", source = "dispatchAt")
    @Mapping(target = "cantProgrammedOrders", source = "programmedOrders")
    Dispatch toDispatch(DispatchEntity dispatchEntity);

    @Mapping(target = "dispatchAt", source = "departureTime")
    @Mapping(target = "analystId", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "analyst", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "routeRequestState", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "programmedOrders", ignore = true)
    DispatchEntity toEntity(DispatchToRegister toRegister);
}
