package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreItemRepository extends JpaRepository<StoredItemEntity, Long> {
    List<StoredItemEntity> findAllByAreaId(Long areaId);
}
