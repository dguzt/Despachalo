package org.guzman.despachalo.adapter.persistence.modules.sync.commodity;

import org.guzman.despachalo.core.storage.domain.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    @Mapping(target = "warehouseId", source = "warehouseId")
    @Mapping(target = "licensePlate", source = "vehiclePlate")
    Shipment toShipment(CommodityEntity entity);
}
