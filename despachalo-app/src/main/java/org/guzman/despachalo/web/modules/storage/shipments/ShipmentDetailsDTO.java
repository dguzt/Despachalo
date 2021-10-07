package org.guzman.despachalo.web.modules.storage.shipments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.storage.application.port.in.OrderItemsChecked;
import org.guzman.despachalo.core.storage.domain.Shipment;
import org.guzman.despachalo.core.sync.domain.Product;

import java.util.List;

@Getter
@AllArgsConstructor
public class ShipmentDetailsDTO {
    private Shipment shipment;
    private List<Product> products;
    private List<OrderItemsChecked> checkedOrders;
}
