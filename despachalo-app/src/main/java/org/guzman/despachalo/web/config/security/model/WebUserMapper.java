package org.guzman.despachalo.web.config.security.model;

import org.guzman.despachalo.adapter.persistence.modules.company.center.DistributionCenterMapper;
import org.guzman.despachalo.adapter.persistence.modules.company.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {DistributionCenterMapper.class})
public interface WebUserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "names")
    @Mapping(target = "lastnames", source = "lastnames")
    @Mapping(target = "center", source = "center")
    @Mapping(target = "roles", ignore = true)
    WebUser toWebUser(UserEntity userEntity);
}

