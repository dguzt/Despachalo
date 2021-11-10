package org.guzman.despachalo.adapter.persistence.modules.sync.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EndPointRepository extends JpaRepository<EndPointEntity, Long> {
    List<EndPointEntity> findAllByCodeIn(List<String> codes);
}
