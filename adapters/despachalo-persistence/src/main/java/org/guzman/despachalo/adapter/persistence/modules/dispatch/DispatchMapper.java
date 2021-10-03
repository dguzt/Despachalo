package org.guzman.despachalo.adapter.persistence.modules.dispatch;

import org.guzman.despachalo.core.programming.domain.Dispatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DispatchMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "analystId", source = "analystId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "departureTime", ignore = true)
    Dispatch toDispatch(DispatchEntity dispatchEntity);
}
