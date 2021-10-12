package org.guzman.despachalo.core.programming.application.port.out;

import java.util.List;

public interface GetCenterWithEndpointCostsPort {
    CenterWithCosts getCenterWithEndpointCosts(Long dispatchId, List<Long> endpointIds);
}
