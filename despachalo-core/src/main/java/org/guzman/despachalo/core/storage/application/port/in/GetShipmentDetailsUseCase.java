package org.guzman.despachalo.core.storage.application.port.in;

import org.guzman.despachalo.core.storage.domain.Shipment;

public interface GetShipmentDetailsUseCase {
    Shipment execute(Long shipmentId);
}
