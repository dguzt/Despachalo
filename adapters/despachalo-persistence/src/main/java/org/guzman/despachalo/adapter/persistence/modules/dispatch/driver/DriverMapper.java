package org.guzman.despachalo.adapter.persistence.modules.dispatch.driver;

import org.guzman.despachalo.core.sync.domain.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "documentNumber", source = "documentNumber")
    Driver toDriver(DriverEntity driverEntity);
}
