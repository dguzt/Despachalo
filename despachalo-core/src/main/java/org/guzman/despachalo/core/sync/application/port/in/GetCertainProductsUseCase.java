package org.guzman.despachalo.core.sync.application.port.in;

import org.guzman.despachalo.core.sync.domain.Product;

import java.util.List;

public interface GetCertainProductsUseCase {
    List<Product> execute(List<Long> productDetailsId);
}
