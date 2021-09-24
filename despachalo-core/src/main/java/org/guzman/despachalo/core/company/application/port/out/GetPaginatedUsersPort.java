package org.guzman.despachalo.core.company.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.domain.User;

public interface GetPaginatedUsersPort {
    Paginator<User> getPage(Filters filters);
}
