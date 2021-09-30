package org.guzman.despachalo.web.modules.storage.shipments;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.in.GetPaginatedShipmentsCase;
import org.guzman.despachalo.core.storage.domain.Shipment;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetPaginatedShipmentsController {
    private final GetPaginatedShipmentsCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/storage/shipments")
    public Paginator<Shipment> getShipments(
            Pageable pageable,
            @RequestParam(value = "search", defaultValue = "") String search) {

        var filters = Filters.builder()
                .page(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .search(search)
                .build();

        return useCase.execute(filters);
    }
}
