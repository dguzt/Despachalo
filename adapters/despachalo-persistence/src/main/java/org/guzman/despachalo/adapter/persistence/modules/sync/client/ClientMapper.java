package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import org.guzman.despachalo.core.sync.load.domain.ClientToRegister;
import org.guzman.despachalo.core.sync.order.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ruc", source = "ruc")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "businessName", source = "businessName")
    Client toClient(ClientEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ruc", source = "ruc")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "businessName", source = "businessName")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ClientEntity toEntity(ClientToRegister clientToRegister);
}
