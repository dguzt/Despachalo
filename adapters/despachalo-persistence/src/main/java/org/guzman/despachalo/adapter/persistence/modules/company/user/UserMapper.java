package org.guzman.despachalo.adapter.persistence.modules.company.user;

import org.guzman.despachalo.core.company.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "centerId", source = "centerId")
    @Mapping(target = "names", source = "names")
    @Mapping(target = "lastnames", source = "lastnames")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "doc.number", source = "documentNumber")
    @Mapping(target = "doc.type", source = "documentType")
    @Mapping(target = "doc", ignore = true)
    @Mapping(target = "role", source = "role")
    @Mapping(target = "isAdmin", source = "isAdmin")
    User toUser(UserEntity entity);
}
