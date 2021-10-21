package org.guzman.despachalo.core.sync.product.application.port.in;

import org.guzman.despachalo.core.sync.product.domain.Product;

import java.util.List;

public interface GetCertainProductsUseCase {
    List<Product> execute(List<Long> productDetailsId);
}
