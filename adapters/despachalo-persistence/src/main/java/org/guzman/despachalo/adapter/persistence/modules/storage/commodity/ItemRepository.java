package org.guzman.despachalo.adapter.persistence.modules.storage.commodity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findAllByCommodityId(Long commodityId);
    List<ItemEntity> findAllByCommodityIdAndOrderId(Long commodityId, Long orderId);
    Integer countAllByCommodityIdAndOrderId(Long commodityId, Long orderId);
    Integer countAllByCommodityId(Long commodityId);
    List<ItemEntity> findAllByOrder_DispatchId(Long dispatchId);
}
