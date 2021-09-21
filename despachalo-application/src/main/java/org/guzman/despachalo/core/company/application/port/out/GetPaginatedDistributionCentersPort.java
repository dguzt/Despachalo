package org.guzman.despachalo.core.company.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.domain.DistributionCenter;

public interface GetPaginatedDistributionCentersPort {
    Paginator<DistributionCenter> getPage(Filters filters);
}
