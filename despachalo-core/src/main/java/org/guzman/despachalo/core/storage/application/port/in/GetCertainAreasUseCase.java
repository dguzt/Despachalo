package org.guzman.despachalo.core.storage.application.port.in;

import org.guzman.despachalo.core.storage.domain.Area;

import java.util.List;

public interface GetCertainAreasUseCase {
    List<Area> execute(List<Long> areaIds);
}
