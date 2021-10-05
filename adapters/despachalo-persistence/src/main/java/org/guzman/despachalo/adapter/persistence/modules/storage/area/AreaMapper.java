package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import org.guzman.despachalo.core.storage.domain.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "availableCapacity", source = "availableCapacity")
    @Mapping(target = "totalCapacity", source = "totalCapacity")
    Area toArea(AreaEntity entity);
}
