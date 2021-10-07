package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByDispatchNotNullOrderByDispatch_DispatchAtDesc(Pageable pageable);
}
