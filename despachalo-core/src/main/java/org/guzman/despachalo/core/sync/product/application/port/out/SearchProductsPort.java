package org.guzman.despachalo.core.sync.product.application.port.out;

import org.guzman.despachalo.core.sync.product.domain.Product;

import java.util.List;

public interface SearchProductsPort {
    List<Product> searchByIds(List<Long> detailIds);
}
