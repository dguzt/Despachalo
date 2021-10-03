package org.guzman.despachalo.core.company.application;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedUsersPort;
import org.guzman.despachalo.core.company.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPaginatedUsersServiceTest {

    @Mock
    private GetPaginatedUsersPort getPaginatedUsersPort;

    @InjectMocks
    private GetPaginatedUsersService getPaginatedUsersService;

    private Filters filters() {
        return Filters.builder()
                .page(1)
                .pageSize(10)
                .search("")
                .build();
    }

    private User user() {
        return new User(1L, "John", "Connor", null, "john.connor@despacho.pe", true, null);
    }

    private Paginator<User> page(List<User> users) {
        return Paginator.<User>builder()
                .page(1)
                .pageSize(10)
                .total(1L)
                .data(users)
                .build();
    }

    @Test
    void whenGetForRolesPage_shouldReturnIt() {
        var user = user();
        var filters = filters();
        var page = page(List.of(user));

        when(getPaginatedUsersPort.getPage(filters))
                .thenReturn(page);

        var result = getPaginatedUsersService.execute(filters);
        assertEquals(result.getPage(), page.getPage());
        assertEquals(result.getData().get(0).getId(), user.getId());
        assertEquals(result.getData().get(0).getEmail(), user.getEmail());
        assertEquals(result.getData().get(0).getActive(), user.getActive());
        assertEquals(result.getData().get(0).getLastnames(), user.getLastnames());
    }
}
