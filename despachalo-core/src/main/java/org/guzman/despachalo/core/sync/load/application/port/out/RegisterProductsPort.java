package org.guzman.despachalo.core.sync.load.application.port.out;

import org.guzman.despachalo.core.sync.load.domain.ProductToRegister;

import java.util.List;

public interface RegisterProductsPort {
    void registerProducts(List<ProductToRegister> toRegister);
}
