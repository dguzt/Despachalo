package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.in.GetPaginatedAreasUseCase;
import org.guzman.despachalo.core.storage.application.port.out.GetPaginatedAreasPort;
import org.guzman.despachalo.core.storage.domain.Area;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedAreasService implements GetPaginatedAreasUseCase {
    private final GetPaginatedAreasPort getPaginatedAreasPort;

    @Override
    public Paginator<Area> execute(Filters filters) {
        return getPaginatedAreasPort.getPage(filters);
    }
}
