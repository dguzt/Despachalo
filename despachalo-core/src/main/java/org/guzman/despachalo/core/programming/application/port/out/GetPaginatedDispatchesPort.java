package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.domain.Dispatch;

public interface GetPaginatedDispatchesPort {
    Paginator<Dispatch> getPage(Filters filters, String state);
}
