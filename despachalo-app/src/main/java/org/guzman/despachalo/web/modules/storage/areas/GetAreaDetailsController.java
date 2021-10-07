package org.guzman.despachalo.web.modules.storage.areas;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.company.application.port.in.GetCenterDetailsUseCase;
import org.guzman.despachalo.core.storage.application.port.in.GetAreaDetailsUseCase;
import org.guzman.despachalo.core.storage.domain.Item;
import org.guzman.despachalo.core.sync.application.port.in.GetCertainProductsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetAreaDetailsController {
    private final GetAreaDetailsUseCase useCase;
    private final GetCertainProductsUseCase productsUseCase;
    private final GetCenterDetailsUseCase centerDetailsUseCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/storage/areas/{areaId}")
    public AreaDetailsDTO getAreaDetails(@PathVariable("areaId") Long areaId) {
        var areaWithDetails = useCase.execute(areaId);
        var center = centerDetailsUseCase.execute(areaWithDetails.getArea().getCenterId());

        var productDetailIds = areaWithDetails.getStoredItems()
                .stream()
                .map(Item::getProductDetailId)
                .collect(Collectors.toList());
        var products = productsUseCase.execute(productDetailIds);

        return new AreaDetailsDTO(areaWithDetails, center, products);
    }
}
