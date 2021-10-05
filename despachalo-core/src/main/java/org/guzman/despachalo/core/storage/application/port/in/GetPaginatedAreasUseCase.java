package org.guzman.despachalo.core.storage.application.port.in;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.domain.Area;

public interface GetPaginatedAreasUseCase {
    Paginator<Area> execute(Filters filters);
}
