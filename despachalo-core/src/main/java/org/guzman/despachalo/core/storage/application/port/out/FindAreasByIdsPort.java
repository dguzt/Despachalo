package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.core.storage.domain.Area;

import java.util.List;

public interface FindAreasByIdsPort {
    List<Area> findAreasByIds(List<Long> areaIds);
}
