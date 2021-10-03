package org.guzman.despachalo.core.company.application;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedRolesPort;
import org.guzman.despachalo.core.company.domain.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPaginatedRolesServiceTest {

    @Mock
    private GetPaginatedRolesPort getPaginatedRolesPort;

    @InjectMocks
    private GetPaginatedRolesService getPaginatedRolesService;

    private Filters filters() {
        return Filters.builder()
                .page(1)
                .pageSize(10)
                .search("")
                .build();
    }

    private Role role() {
        return new Role(1L, "ADMIN", LocalDateTime.now(), null);
    }

    private Paginator<Role> page(List<Role> roles) {
        return Paginator.<Role>builder()
                .page(1)
                .pageSize(10)
                .total(1L)
                .data(roles)
                .build();
    }

    @Test
    void whenGetForRolesPage_shouldReturnIt() {
        var role = role();
        var filters = filters();
        var page = page(List.of(role));

        when(getPaginatedRolesPort.getPage(filters))
                .thenReturn(page);

        var result = getPaginatedRolesService.execute(filters);
        assertEquals(result.getPage(), page.getPage());
        assertEquals(result.getData().get(0).getId(), role.getId());
        assertEquals(result.getData().get(0).getName(), role.getName());
    }
}
