package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityRepository extends JpaRepository<CommodityEntity, Long> {
    Page<CommodityEntity> findAllByOrderByArrivalTimeDesc(Pageable pageable);
    List<CommodityEntity> findAllByCodeIn(List<String> codes);
}
