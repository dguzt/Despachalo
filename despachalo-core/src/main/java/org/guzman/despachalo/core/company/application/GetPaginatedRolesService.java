package org.guzman.despachalo.core.company.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.in.GetPaginatedRolesUseCase;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedRolesPort;
import org.guzman.despachalo.core.company.domain.Role;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedRolesService implements GetPaginatedRolesUseCase {
    private final GetPaginatedRolesPort getPaginatedRolesPort;

    @Override
    public Paginator<Role> execute(Filters filters) {
        return getPaginatedRolesPort.getPage(filters);
    }
}
