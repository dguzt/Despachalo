package org.guzman.despachalo.core.storage.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.storage.application.port.in.GetShipmentDetailsUseCase;
import org.guzman.despachalo.core.storage.application.port.out.GetShipmentDetailsPort;
import org.guzman.despachalo.core.storage.domain.Shipment;

@UseCase
@RequiredArgsConstructor
public class GetShipmentDetailsService implements GetShipmentDetailsUseCase {
    private final GetShipmentDetailsPort getShipmentPort;

    @Override
    public Shipment execute(Long shipmentId) {
        return getShipmentPort.getShipmentDetails(shipmentId)
                .orElseThrow(() -> new ShipmentNotFoundException(shipmentId));
    }
}
