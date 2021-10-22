package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.load.domain.Load;

public interface GetPaginatedLoadsPort {
    Paginator<Load> getPage(Filters filters, String state);
}
