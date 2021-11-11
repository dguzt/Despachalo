package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import org.guzman.despachalo.core.sync.load.domain.CommodityToRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommodityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "geocode", source = "deliveryGeocode")
    @Mapping(target = "arrivalTime", source = "arrivalDate")
    @Mapping(target = "assignedCenterId", source = "centerId")
    @Mapping(target = "warehouseId", source = "originPointId")
    @Mapping(target = "vehiclePlate", source = "plate")
    @Mapping(target = "originPoint", ignore = true)
    @Mapping(target = "assignedCenter", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CommodityEntity toEntity(CommodityToRegister commodityToRegister);
}
