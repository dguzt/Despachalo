package org.guzman.despachalo.adapter.persistence.modules.company.center;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistributionCenterRepository extends JpaRepository<DistributionCenterEntity, Long> {
    Page<DistributionCenterEntity> findAllByNameContainingIgnoreCase(Pageable pageable, String name);
    Boolean existsByIdAndDeletedIsFalse(Long id);
    List<DistributionCenterEntity> findAllByDeletedIsFalse();
    Optional<DistributionCenterEntity> findByIdAndDeletedIsFalse(Long centerId);

    List<DistributionCenterEntity> findAllByGeocodeIn(List<String> geocodes);
}
