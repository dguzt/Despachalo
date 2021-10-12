package org.guzman.despachalo.adapter.persistence.modules.programming;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammedVehicleRepository extends JpaRepository<ProgrammedVehicleEntity, Long> {
    List<ProgrammedVehicleEntity> findAllByDispatchId(Long dispatchId);
}
