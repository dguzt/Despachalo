package org.guzman.despachalo.adapter.web.modules.company;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.in.GetPaginatedDistributionCentersUseCase;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetPaginatedDistributionCentersController {
    private final GetPaginatedDistributionCentersUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/company/centers")
    public Paginator<DistributionCenter> getTruck(
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
