package org.guzman.despachalo.adapter.persistence.modules.sync.product;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.sync.product.application.port.out.SearchProductsPort;
import org.guzman.despachalo.core.sync.product.domain.Product;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements SearchProductsPort {
    private final ProductDetailRepository productDetailRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> searchByIds(List<Long> detailIds) {
        var rows = productDetailRepository.findAllByIdIn(detailIds);
        return productMapper.toProducts(rows);
    }
}
