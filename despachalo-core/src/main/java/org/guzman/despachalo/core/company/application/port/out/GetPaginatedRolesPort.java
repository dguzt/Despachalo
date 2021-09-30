package org.guzman.despachalo.core.company.application.port.out;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.domain.Role;
import org.guzman.despachalo.core.company.domain.User;

public interface GetPaginatedRolesPort {
    Paginator<Role> getPage(Filters filters);
}
