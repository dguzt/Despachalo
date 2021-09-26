package org.guzman.despachalo.adapter.persistence.modules.company.user;

import org.guzman.despachalo.core.company.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    Role toRole(RoleEntity entity);
}
