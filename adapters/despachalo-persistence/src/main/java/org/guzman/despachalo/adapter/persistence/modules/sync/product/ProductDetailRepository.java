package org.guzman.despachalo.adapter.persistence.modules.sync.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {
    List<ProductDetailEntity> findAllByIdIn(List<Long> ids);
    Optional<ProductDetailEntity> findByProduct_Code(String code);
}
