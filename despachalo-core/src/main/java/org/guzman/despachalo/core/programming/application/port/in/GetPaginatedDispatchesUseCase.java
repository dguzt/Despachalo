package org.guzman.despachalo.core.programming.application.port.in;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.domain.Dispatch;

public interface GetPaginatedDispatchesUseCase {
    Paginator<Dispatch> execute(Filters filters, String state);
}
