package org.guzman.despachalo.core.company.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.in.GetPaginatedDistributionCentersUseCase;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedDistributionCentersPort;
import org.guzman.despachalo.core.company.domain.DistributionCenter;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedDistributionCentersService implements GetPaginatedDistributionCentersUseCase {
    private final GetPaginatedDistributionCentersPort getPaginatedDistributionCentersPort;

    @Override
    public Paginator<DistributionCenter> execute(Filters filters) {
        return getPaginatedDistributionCentersPort.getPage(filters);
    }
}
