package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.in.GetPaginatedUsersUseCase;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedUsersPort;
import org.guzman.despachalo.core.company.domain.User;

@UseCase
@RequiredArgsConstructor
public class GetPaginatedDispatchsService implements GetPaginatedUsersUseCase {
    private final GetPaginatedUsersPort getPaginatedUsersPort;

    @Override
    public Paginator<User> execute(Filters filters) {
        return getPaginatedUsersPort.getPage(filters);
    }
}
