package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import org.guzman.despachalo.core.sync.load.domain.DestinationPointToRegister;
import org.guzman.despachalo.core.sync.points.destination.domain.DestinationPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EndPointMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "geoCode", source = "geoCode")
    DestinationPoint toDestinationPoint(EndPointEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "geoCode", source = "geoCode")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "centerId", source = "centerId")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "latitude", source = "location.latitude")
    @Mapping(target = "longitude", source = "location.longitude")
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "center", ignore = true)
    EndPointEntity toEntity(DestinationPointToRegister toRegister);
}
