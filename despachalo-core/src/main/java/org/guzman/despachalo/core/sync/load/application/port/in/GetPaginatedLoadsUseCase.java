package org.guzman.despachalo.core.sync.load.application.port.in;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.load.domain.Load;

public interface GetPaginatedLoadsUseCase {
    Paginator<Load> execute(Filters filters, String state);
}
