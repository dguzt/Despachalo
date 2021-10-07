package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.core.storage.domain.Area;

import java.util.Optional;

public interface FindAreaPort {
    Optional<Area> findArea(Long areaId);
}
