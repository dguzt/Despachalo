package org.guzman.despachalo.adapter.persistence.modules.company.center;

import org.guzman.despachalo.core.company.application.port.in.CenterData;
import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistributionCenterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "latitude", source = "location.latitude")
    @Mapping(target = "longitude", source = "location.longitude")
    @Mapping(target = "geocode", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    DistributionCenterEntity toEntity(DistributionCenterToRegister toRegister);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "location.latitude", source = "latitude")
    @Mapping(target = "location.longitude", source = "longitude")
    DistributionCenter toDistributionCenter(DistributionCenterEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CenterData toCenterData(DistributionCenterEntity entity);
}
