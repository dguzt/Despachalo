package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.application.port.in.GetPaginatedDispatchesUseCase;
import org.guzman.despachalo.core.programming.application.port.out.GetPaginatedDispatchesPort;
import org.guzman.despachalo.core.programming.domain.Dispatch;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedDispatchesService implements GetPaginatedDispatchesUseCase {
    private final GetPaginatedDispatchesPort getPaginatedDispatchesPort;

    @Override
    public Paginator<Dispatch> execute(Filters filters) {
        return getPaginatedDispatchesPort.getPage(filters);
    }
}
