package org.guzman.despachalo.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<TruckEntity, Long> {
}
