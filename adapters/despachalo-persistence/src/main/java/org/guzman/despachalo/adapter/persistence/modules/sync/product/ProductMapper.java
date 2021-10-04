package org.guzman.despachalo.adapter.persistence.modules.sync.product;

import org.guzman.despachalo.core.sync.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "code", source = "product.code")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "detailsId", source = "id")
    @Mapping(target = "volume", source = "volume")
    @Mapping(target = "weight", source = "weight")
    Product toProduct(ProductDetailEntity entity);

    List<Product> toProducts(List<ProductDetailEntity> entities);
}
