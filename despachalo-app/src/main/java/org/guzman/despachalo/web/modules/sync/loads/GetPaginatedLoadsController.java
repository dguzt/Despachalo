package org.guzman.despachalo.web.modules.sync.loads;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.load.application.port.in.GetPaginatedLoadsUseCase;
import org.guzman.despachalo.core.sync.load.domain.Load;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetPaginatedLoadsController {
    private final GetPaginatedLoadsUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/sync/loads")
    public Paginator<Load> getShipments(
            Pageable pageable,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "state", defaultValue = "") String state) {

        var filters = Filters.builder()
                .page(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .search(search)
                .build();

        return useCase.execute(filters, state);
    }
}
