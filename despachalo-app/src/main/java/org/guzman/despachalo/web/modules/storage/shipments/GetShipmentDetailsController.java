package org.guzman.despachalo.web.modules.storage.shipments;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.storage.application.port.in.GetShipmentDetailsUseCase;
import org.guzman.despachalo.core.storage.domain.Shipment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetShipmentDetailsController {
    private final GetShipmentDetailsUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/storage/shipments/{shipmentId}")
    public Shipment getShipments(@PathVariable("shipmentId") Long shipmentId) {
        return useCase.execute(shipmentId);
    }
}
