package org.guzman.despachalo.adapter.persistence.modules.company.user;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedUsersPort;
import org.guzman.despachalo.core.company.domain.User;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements GetPaginatedUsersPort {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public Paginator<User> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAll(pageable);

        var data = page.getContent()
                .stream()
                .map(mapper::toUser)
                .collect(Collectors.toList());

        return Paginator.<User>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }
}
