package org.guzman.despachalo.core.sync.product.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.sync.product.application.port.in.GetCertainProductsUseCase;
import org.guzman.despachalo.core.sync.product.application.port.out.SearchProductsPort;
import org.guzman.despachalo.core.sync.product.domain.Product;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetCertainProductsService implements GetCertainProductsUseCase {
    private final SearchProductsPort searchProductsPort;

    @Override
    public List<Product> execute(List<Long> productDetailsId) {
        return searchProductsPort.searchByIds(productDetailsId);
    }
}
