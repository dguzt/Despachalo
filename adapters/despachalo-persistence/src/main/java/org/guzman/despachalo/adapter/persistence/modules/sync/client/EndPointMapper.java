package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import org.guzman.despachalo.core.sync.points.destination.domain.DestinationPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EndPointMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "geoCode", source = "geoCode")
    DestinationPoint toDestinationPoint(EndPointEntity entity);
}
