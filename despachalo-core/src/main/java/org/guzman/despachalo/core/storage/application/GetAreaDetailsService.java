package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.AreaWithDetails;
import org.guzman.despachalo.core.storage.application.port.in.GetAreaDetailsUseCase;
import org.guzman.despachalo.core.storage.application.port.out.FindAreaPort;
import org.guzman.despachalo.core.storage.application.port.out.GetItemsStoredInAreaPort;

@UseCase
@RequiredArgsConstructor
public class GetAreaDetailsService implements GetAreaDetailsUseCase {
    private final FindAreaPort findAreaPort;
    private final GetItemsStoredInAreaPort getItemsStoredInAreaPort;

    @Override
    public AreaWithDetails execute(Long areaId) {
        var area = findAreaPort.findArea(areaId)
                .orElseThrow(() -> new AreaNotFoundException(areaId));

        var items = getItemsStoredInAreaPort.getItemsStoredInArea(areaId);
        return new AreaWithDetails(area, items);
    }
}
