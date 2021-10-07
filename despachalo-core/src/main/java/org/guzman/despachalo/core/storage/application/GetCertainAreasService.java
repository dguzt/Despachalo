package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.GetCertainAreasUseCase;
import org.guzman.despachalo.core.storage.application.port.out.FindAreasByIdsPort;
import org.guzman.despachalo.core.storage.domain.Area;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCertainAreasService implements GetCertainAreasUseCase {
    private final FindAreasByIdsPort areasByIdsPort;

    @Override
    public List<Area> execute(List<Long> areaIds) {
        return areasByIdsPort.findAreasByIds(areaIds);
    }
}
