package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OriginPointRepository extends JpaRepository<OriginPointEntity, Long> {
    List<OriginPointEntity> findAllByCodeIn(List<String> codes);
}
