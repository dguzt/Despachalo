package org.guzman.despachalo.adapter.persistence.modules.sync.load;

import org.guzman.despachalo.adapter.persistence.modules.company.user.UserMapper;
import org.guzman.despachalo.core.sync.load.domain.Load;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface LoadMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "loadedAt", source = "syncAt")
    @Mapping(target = "dataType", source = "dataType")
    @Mapping(target = "responsible", source = "responsible")
    Load toLoad(SyncEntity entity);
}
