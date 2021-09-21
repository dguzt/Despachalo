package org.guzman.despachalo.core.company.application.port.in;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.domain.User;

public interface GetPaginatedUsersUseCase {
    Paginator<User> execute(Filters filters);
}
