package org.guzman.despachalo.adapter.persistence.modules.dispatch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispatchRepository extends JpaRepository<DispatchEntity, Long> {
    Page<DispatchEntity> findAllByStateOrderByDispatchAtDesc(String state, Pageable pageable);
    Optional<DispatchEntity> findTopByRouteRequestStateOrderByCreatedAtDesc(String requestState);
}
