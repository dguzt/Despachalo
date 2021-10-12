package org.guzman.despachalo.adapter.persistence.modules.route;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    List<RouteEntity> findAllByProgrammedVehicleIdOrderByDeliveryOrderAsc(Long vehicleId);
}
