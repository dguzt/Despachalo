package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import org.guzman.despachalo.core.storage.domain.Item;
import org.guzman.despachalo.core.sync.load.domain.OriginPointToRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OriginPointMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", source = "code")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OriginPointEntity toEntity(OriginPointToRegister toRegister);
}
