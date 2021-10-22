package org.guzman.despachalo.adapter.persistence.modules.sync.load;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SyncRepository extends JpaRepository<SyncEntity, Long> {
    Page<SyncEntity> findAllByStateContainsOrderBySyncAtDesc(String state, Pageable pageable);
}
