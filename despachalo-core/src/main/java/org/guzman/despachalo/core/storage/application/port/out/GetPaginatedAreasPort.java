package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.domain.Area;

public interface GetPaginatedAreasPort {
    Paginator<Area> getPage(Filters filters);
}
