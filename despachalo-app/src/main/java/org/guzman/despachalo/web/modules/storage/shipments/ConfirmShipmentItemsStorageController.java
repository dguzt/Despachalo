package org.guzman.despachalo.web.modules.storage.shipments;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.WebAdapter;
import org.guzman.despachalo.core.storage.application.port.in.ConfirmAllShipmentItemsStorageUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ConfirmShipmentItemsStorageController {
    private final ConfirmAllShipmentItemsStorageUseCase useCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/storage/shipments/{shipmentId}/items")
    public void confirmShipmentItemsStorage(
            @PathVariable("shipmentId") Long shipmentId,
            @RequestBody ConfirmInfo confirmInfo) {

        useCase.execute(confirmInfo.getCenterId(),
                confirmInfo.getAreaId(),
                shipmentId,
                confirmInfo.getOrderIds()
        );
    }
}
