package org.guzman.despachalo.web.modules.storage.shipments;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.storage.application.port.in.GetShipmentDetailsUseCase;
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
public class GetShipmentDetailsController {
    private final GetShipmentDetailsUseCase useCase;
    private final GetCertainProductsUseCase productsUseCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/storage/shipments/{shipmentId}")
    public ShipmentDetailsDTO getShipments(@PathVariable("shipmentId") Long shipmentId) {
        var shipment = useCase.execute(shipmentId);

        var productDetailIds = shipment.getItems()
                .stream()
                .map(Item::getProductDetailId)
                .collect(Collectors.toList());

        var products = productsUseCase.execute(productDetailIds);

        return new ShipmentDetailsDTO(shipment, products);
    }
}
