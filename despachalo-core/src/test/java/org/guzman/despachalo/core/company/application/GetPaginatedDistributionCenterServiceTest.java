package org.guzman.despachalo.core.company.application;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedDistributionCentersPort;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPaginatedDistributionCenterServiceTest {

    @Mock
    private GetPaginatedDistributionCentersPort getPaginatedDistributionCentersPort;

    @InjectMocks
    private GetPaginatedDistributionCentersService getPaginatedDistributionCentersService;

    private Filters filters() {
        return Filters.builder()
                .page(1)
                .pageSize(10)
                .search("")
                .build();
    }

    private DistributionCenter center() {
        return new DistributionCenter(1L, "Urubamba", null, "Dirección");
    }

    private Paginator<DistributionCenter> page(List<DistributionCenter> centers) {
        return Paginator.<DistributionCenter>builder()
                .page(1)
                .pageSize(10)
                .data(centers)
                .total(1L)
                .build();
    }

    @Test
    void whenGetForCentersPage_shouldReturnIt() {
        var center = center();
        var filters = filters();
        var page = page(List.of(center));

        when(getPaginatedDistributionCentersPort.getPage(filters))
                .thenReturn(page);

        var result = getPaginatedDistributionCentersService.execute(filters);
        assertEquals(result.getPage(), page.getPage());
        assertEquals(result.getData().get(0).getId(), center.getId());
        assertEquals(result.getData().get(0).getName(), center.getName());
    }
}
