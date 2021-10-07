package org.guzman.despachalo.core.storage.application.port.out;

public interface CheckForShipmentCompletedPort {
    void checkForShipmentCompleted(Long shipmentId);
}
