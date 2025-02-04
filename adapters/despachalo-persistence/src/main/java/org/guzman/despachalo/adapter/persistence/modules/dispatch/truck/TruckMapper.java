package org.guzman.despachalo.adapter.persistence.modules.dispatch.truck;

import org.guzman.despachalo.core.sync.vehicle.domain.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TruckMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "plate", source = "vehiclePlate")
    Truck toTruck(TruckEntity truckEntity);
}
