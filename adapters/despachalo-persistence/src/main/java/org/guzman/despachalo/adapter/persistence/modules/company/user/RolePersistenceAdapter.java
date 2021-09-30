package org.guzman.despachalo.adapter.persistence.modules.company.user;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedRolesPort;
import org.guzman.despachalo.core.company.domain.Role;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class RolePersistenceAdapter implements GetPaginatedRolesPort {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public Paginator<Role> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAll(pageable);

        var data = page.getContent()
                .stream()
                .map(mapper::toRole)
                .collect(Collectors.toList());

        return Paginator.<Role>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }
}
