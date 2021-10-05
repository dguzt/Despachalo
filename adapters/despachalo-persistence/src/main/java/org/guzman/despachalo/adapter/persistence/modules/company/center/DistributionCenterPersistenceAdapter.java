package org.guzman.despachalo.adapter.persistence.modules.company.center;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.in.CenterData;
import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.application.port.out.CheckIfCenterExistsPort;
import org.guzman.despachalo.core.company.application.port.out.GetAllCentersDataPort;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedDistributionCentersPort;
import org.guzman.despachalo.core.company.application.port.out.RegisterDistributionCenterPort;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DistributionCenterPersistenceAdapter implements
        RegisterDistributionCenterPort,
        GetPaginatedDistributionCentersPort,
        CheckIfCenterExistsPort,
        GetAllCentersDataPort {

    private final DistributionCenterRepository repository;
    private final DistributionCenterMapper mapper;

    @Override
    public DistributionCenter register(DistributionCenterToRegister toRegister) {
        var row = mapper.toEntity(toRegister);
        var saved = repository.save(row);
        return mapper.toDistributionCenter(saved);
    }

    @Override
    public Paginator<DistributionCenter> getPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAllByNameContainingIgnoreCase(pageable, filters.getSearch());

        var data = page.getContent()
                .stream()
                .map(mapper::toDistributionCenter)
                .collect(Collectors.toList());

        return Paginator.<DistributionCenter>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }

    @Override
    public Boolean checkIfCenterExists(Long centerId) {
        return repository.existsByIdAndDeletedIsFalse(centerId);
    }

    @Override
    public List<CenterData> getAllCenters() {
        return repository.findAllByDeletedIsFalse()
                .stream()
                .map(mapper::toCenterData)
                .collect(Collectors.toList());
    }
}
