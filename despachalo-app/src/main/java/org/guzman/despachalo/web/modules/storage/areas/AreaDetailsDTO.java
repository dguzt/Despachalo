package org.guzman.despachalo.web.modules.storage.areas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.storage.application.port.in.AreaWithDetails;
import org.guzman.despachalo.core.sync.domain.Product;

import java.util.List;

@Getter
@AllArgsConstructor
public class AreaDetailsDTO {
    private AreaWithDetails details;
    private List<Product> products;
}
