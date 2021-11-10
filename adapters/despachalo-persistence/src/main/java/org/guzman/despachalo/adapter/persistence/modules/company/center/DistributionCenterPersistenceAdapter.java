package org.guzman.despachalo.adapter.persistence.modules.company.center;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.company.application.port.in.CenterData;
import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.application.port.out.*;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.guzman.despachalo.core.sync.load.application.port.out.FindCentersByGeocodesPort;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DistributionCenterPersistenceAdapter implements
        RegisterDistributionCenterPort,
        GetPaginatedDistributionCentersPort,
        CheckIfCenterExistsPort,
        GetAllCentersDataPort,
        FindCenterPort,
        FindCentersByGeocodesPort {

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

    @Override
    public Optional<DistributionCenter> findCenter(Long centerId) {
        return repository.findByIdAndDeletedIsFalse(centerId)
                .map(mapper::toDistributionCenter);
    }

    @Override
    public Map<String, Long> findCentersByGeocodes(List<String> geocodes) {
        return repository.findAllByGeocodeIn(geocodes)
                .stream()
                .collect(Collectors.toMap(
                        DistributionCenterEntity::getGeocode,
                        DistributionCenterEntity::getId));
    }
}
