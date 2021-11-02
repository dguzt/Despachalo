package org.guzman.despachalo.adapter.persistence.modules.sync.product;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterProductsPort;
import org.guzman.despachalo.core.sync.load.domain.ProductToRegister;
import org.guzman.despachalo.core.sync.product.application.port.out.SearchProductsPort;
import org.guzman.despachalo.core.sync.product.domain.Product;

import java.util.List;
import java.util.Objects;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements SearchProductsPort, RegisterProductsPort {
    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailsMapper productDetailsMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> searchByIds(List<Long> detailIds) {
        var rows = productDetailRepository.findAllByIdIn(detailIds);
        return productMapper.toProducts(rows);
    }

    @Override
    public void registerProducts(List<ProductToRegister> toRegister) {
        toRegister.stream()
                .map(product -> {
                    var row = productDetailsMapper.toProductDetailsEntity(product);
                    var foundOpt = productDetailRepository.findByProduct_Code(product.getCode());
                    if (foundOpt.isPresent()) {
                        var found = foundOpt.get();
                        row.setCreatedAt(found.getCreatedAt());
                        row.setProductId(found.getProductId());
                        row.getProduct().setId(found.getProduct().getId());
                        row.getProduct().setCreatedAt(found.getProduct().getCreatedAt());
                        if (Objects.equals(row.getVolume(), found.getVolume()) && Objects.equals(row.getWeight(), found.getWeight())) {
                            row.setId(found.getId());
                        }
                    }
                    return row;
                })
                .forEach(detailsRow -> {
                    var productRow = detailsRow.getProduct();
                    var saved = productRepository.save(productRow);
                    detailsRow.setProduct(null);
                    detailsRow.setProductId(saved.getId());
                    productDetailRepository.save(detailsRow);
                });
    }
}
