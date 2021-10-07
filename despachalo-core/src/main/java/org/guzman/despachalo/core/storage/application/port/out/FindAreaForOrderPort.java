package org.guzman.despachalo.core.storage.application.port.out;

import java.util.Optional;

public interface FindAreaForOrderPort {
    Optional<Long> findAreaForOrder(Long orderId);
}
