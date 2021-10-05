package org.guzman.despachalo.adapter.persistence.modules.company.center;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributionCenterRepository extends JpaRepository<DistributionCenterEntity, Long> {
    Page<DistributionCenterEntity> findAllByNameContainingIgnoreCase(Pageable pageable, String name);
    Boolean existsByIdAndDeletedIsFalse(Long id);
}
