package org.guzman.despachalo.adapter.persistence.modules.storage.area;

import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
}
