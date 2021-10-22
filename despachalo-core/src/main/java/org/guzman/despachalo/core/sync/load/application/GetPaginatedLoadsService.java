package org.guzman.despachalo.core.sync.load.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.load.application.port.in.GetPaginatedLoadsUseCase;
import org.guzman.despachalo.core.sync.load.application.port.out.GetPaginatedLoadsPort;
import org.guzman.despachalo.core.sync.load.domain.Load;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedLoadsService implements GetPaginatedLoadsUseCase {
    private final GetPaginatedLoadsPort loadsPort;

    @Override
    public Paginator<Load> execute(Filters filters, String state) {
        return loadsPort.getPage(filters, state);
    }
}
