package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import org.guzman.despachalo.core.storage.application.port.in.AreaToRegister;
import org.guzman.despachalo.core.storage.domain.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "availableCapacity", source = "availableCapacity")
    @Mapping(target = "totalCapacity", source = "totalCapacity")
    @Mapping(target = "centerId", source = "centerId")
    Area toArea(AreaEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "availableCapacity", ignore = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "totalCapacity", source = "totalCapacity")
    @Mapping(target = "centerId", source = "centerId")
    @Mapping(target = "center", ignore = true)
    AreaEntity toEntity(AreaToRegister toRegister);
}
