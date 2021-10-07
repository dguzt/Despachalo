package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreItemRepository extends JpaRepository<StoredItemEntity, Long> {
    List<StoredItemEntity> findAllByAreaId(Long areaId);
    Optional<StoredItemEntity> findTopByItem_OrderIdOrderByStoredAtAsc(Long orderId);
    Integer countAllByItem_CommodityIdAndItem_OrderId(Long commodityId, Long orderId);
    Integer countAllByItem_CommodityId(Long commodityId);
}
