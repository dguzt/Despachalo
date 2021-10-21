package org.guzman.despachalo.web.modules.storage.areas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.company.domain.DistributionCenter;
import org.guzman.despachalo.core.storage.application.port.in.AreaWithDetails;
import org.guzman.despachalo.core.sync.product.domain.Product;

import java.util.List;

@Getter
@AllArgsConstructor
public class AreaDetailsDTO {
    private AreaWithDetails details;
    private DistributionCenter center;
    private List<Product> products;
}
