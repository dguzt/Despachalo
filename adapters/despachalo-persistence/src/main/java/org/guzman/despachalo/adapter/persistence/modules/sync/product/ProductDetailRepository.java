package org.guzman.despachalo.adapter.persistence.modules.sync.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {
    List<ProductDetailEntity> findAllByIdIn(List<Long> ids);
}
