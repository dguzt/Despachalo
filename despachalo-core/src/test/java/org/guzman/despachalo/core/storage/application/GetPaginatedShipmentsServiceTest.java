package org.guzman.despachalo.core.storage.application;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.out.GetPaginatedShipmentsPort;
import org.guzman.despachalo.core.storage.domain.Shipment;
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
public class GetPaginatedShipmentsServiceTest {

    @Mock
    private GetPaginatedShipmentsPort getPaginatedShipmentsPort;

    @InjectMocks
    private GetPaginatedShipmentsService getPaginatedShipmentsService;

    private Filters filters() {
        return Filters.builder()
                .page(1)
                .pageSize(10)
                .search("")
                .build();
    }

    private Shipment shipment() {
        return new Shipment(1L, 1L,1L, "123", "POR LLEGAR", null, LocalDateTime.now(), null);
    }

    private Paginator<Shipment> page(List<Shipment> shipments) {
        return Paginator.<Shipment>builder()
                .page(1)
                .pageSize(10)
                .total(1L)
                .data(shipments)
                .build();
    }

    @Test
    void whenGetForShipmentsPage_shouldReturnIt() {
        var shipment = shipment();
        var filters = filters();
        var page = page(List.of(shipment));

        when(getPaginatedShipmentsPort.getPage(filters))
                .thenReturn(page);

        var result = getPaginatedShipmentsService.execute(filters);
        assertEquals(result.getPage(), page.getPage());
        assertEquals(result.getData().get(0), shipment);
        assertEquals(result.getData().get(0).getId(), shipment.getId());
        assertEquals(result.getData().get(0).getCode(), shipment.getCode());
        assertEquals(result.getData().get(0).getWarehouseId(), shipment.getWarehouseId());
    }
}
