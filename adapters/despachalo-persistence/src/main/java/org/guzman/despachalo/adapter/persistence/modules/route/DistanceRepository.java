package org.guzman.despachalo.adapter.persistence.modules.route;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistanceRepository extends JpaRepository<DistanceEntity, Long> {
    List<DistanceEntity> findAllByOutputNodeIdAndInputNodeIdIn(Long outputNodeId, List<Long> inputNodeIds);
    List<DistanceEntity> findAllByCenterNodeIdAndInputNodeIdIn(Long centerId, List<Long> inputNodeIds);
}
