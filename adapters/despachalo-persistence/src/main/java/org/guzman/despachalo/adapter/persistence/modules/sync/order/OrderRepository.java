package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByStateOrderByCreatedAtDesc(String state, Pageable pageable);
    List<OrderEntity> findAllByStateOrderByCreatedAtDesc(String state);
    List<OrderEntity> findAllByIdIn(List<Long> orderIds);
    List<OrderEntity> findAllByDispatchId(Long dispatchId);
}
