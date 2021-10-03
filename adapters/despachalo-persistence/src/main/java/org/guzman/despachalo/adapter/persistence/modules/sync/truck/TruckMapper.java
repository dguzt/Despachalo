package org.guzman.despachalo.adapter.persistence.modules.sync.truck;

import org.guzman.despachalo.core.sync.domain.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TruckMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    Truck toTruck(TruckEntity truckEntity);
}
