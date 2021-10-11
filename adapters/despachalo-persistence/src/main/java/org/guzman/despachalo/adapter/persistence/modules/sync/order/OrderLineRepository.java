package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Long> {

    List<OrderLineEntity> findAllByOrderId(Long orderId);
}
