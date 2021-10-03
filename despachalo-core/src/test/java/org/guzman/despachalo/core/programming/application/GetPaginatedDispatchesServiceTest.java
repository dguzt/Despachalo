package org.guzman.despachalo.core.programming.application;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.application.port.out.GetPaginatedDispatchesPort;
import org.guzman.despachalo.core.programming.domain.Dispatch;
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
public class GetPaginatedDispatchesServiceTest {

    @Mock
    private GetPaginatedDispatchesPort getPaginatedDispatchesPort;

    @InjectMocks
    private GetPaginatedDispatchesService getPaginatedDispatchesService;

    private Filters filters() {
        return Filters.builder()
                .page(1)
                .pageSize(10)
                .search("")
                .build();
    }

    private Dispatch dispatch() {
        return new Dispatch(1L, LocalDateTime.now(), "PENDIENTE", 1L);
    }

    private Paginator<Dispatch> page(List<Dispatch> dispatches) {
        return Paginator.<Dispatch>builder()
                .page(1)
                .pageSize(10)
                .total(1L)
                .data(dispatches)
                .build();
    }

    @Test
    void whenGetForRolesPage_shouldReturnIt() {
        var dispatch = dispatch();
        var filters = filters();
        var page = page(List.of(dispatch));

        when(getPaginatedDispatchesPort.getPage(filters))
                .thenReturn(page);

        var result = getPaginatedDispatchesService.execute(filters);
        assertEquals(result.getPage(), page.getPage());
        assertEquals(result.getData().get(0), dispatch);
        assertEquals(result.getData().get(0).getId(), dispatch.getId());
        assertEquals(result.getData().get(0).getState(), dispatch.getState());
    }
}
