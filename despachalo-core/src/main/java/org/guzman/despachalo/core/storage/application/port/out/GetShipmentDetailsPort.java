package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.core.storage.domain.Shipment;

import java.util.Optional;

public interface GetShipmentDetailsPort {
    Optional<Shipment> getShipmentDetails(Long shipmentId);
}
