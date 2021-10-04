package org.guzman.despachalo.core.sync.application.port.out;

import org.guzman.despachalo.core.sync.domain.Product;

import java.util.List;

public interface SearchProductsPort {
    List<Product> searchByIds(List<Long> detailIds);
}
