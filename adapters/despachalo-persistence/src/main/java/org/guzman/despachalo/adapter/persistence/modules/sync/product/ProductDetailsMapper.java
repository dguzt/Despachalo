package org.guzman.despachalo.adapter.persistence.modules.sync.product;

import org.guzman.despachalo.core.sync.load.domain.ProductToRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "volume", source = "volume")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "product.id", ignore = true)
    @Mapping(target = "product.code", source = "code")
    @Mapping(target = "product.description", source = "description")
    @Mapping(target = "product.updatedAt", source = "updatedAt")
    @Mapping(target = "product.createdAt", source = "createdAt")
    ProductDetailEntity toProductDetailsEntity(ProductToRegister toRegister);
}
