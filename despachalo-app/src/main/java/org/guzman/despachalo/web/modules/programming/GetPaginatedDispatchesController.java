package org.guzman.despachalo.web.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.application.port.in.GetPaginatedDispatchesUseCase;
import org.guzman.despachalo.core.programming.domain.Dispatch;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.guzman.despachalo.core.programming.domain.DispatchState.PENDING;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetPaginatedDispatchesController {
    private final GetPaginatedDispatchesUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/programming/dispatches")
    public Paginator<Dispatch> getDispatches(
            Pageable pageable,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "state", defaultValue = PENDING) String state) {

        var filters = Filters.builder()
                .page(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .search(search)
                .build();

        return useCase.execute(filters, state);
    }
}
