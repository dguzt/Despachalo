package org.guzman.despachalo.web.modules.storage.shipments;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.storage.application.port.in.CheckForAllShipmentOrdersItemsUseCase;
import org.guzman.despachalo.core.storage.application.port.in.GetCertainAreasUseCase;
import org.guzman.despachalo.core.storage.application.port.in.GetShipmentDetailsUseCase;
import org.guzman.despachalo.core.storage.application.port.in.OrderItemsChecked;
import org.guzman.despachalo.core.storage.domain.Item;
import org.guzman.despachalo.core.sync.product.application.port.in.GetCertainProductsUseCase;
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
    private final CheckForAllShipmentOrdersItemsUseCase ordersItemsUseCase;
    private final GetCertainAreasUseCase areasUseCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/storage/shipments/{shipmentId}")
    public ShipmentDetailsDTO getShipments(@PathVariable("shipmentId") Long shipmentId) {
        var shipment = useCase.execute(shipmentId);

        var productDetailIds = shipment.getItems()
                .stream()
                .map(Item::getProductDetailId)
                .collect(Collectors.toList());
        var products = productsUseCase.execute(productDetailIds);

        var ordersIds = shipment.getItems()
                .stream()
                .map(Item::getOrderId)
                .distinct()
                .collect(Collectors.toList());
        var checkedOrders = ordersItemsUseCase.execute(ordersIds, shipmentId, shipment.getAssignedCenterId());

        var areaIds = checkedOrders.stream()
                .map(OrderItemsChecked::getZoneAssignedId)
                .collect(Collectors.toList());
        var areas = areasUseCase.execute(areaIds);

        return new ShipmentDetailsDTO(shipment, products, checkedOrders, areas);
    }
}
