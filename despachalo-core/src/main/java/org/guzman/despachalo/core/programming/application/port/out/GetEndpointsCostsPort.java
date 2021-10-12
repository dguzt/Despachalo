package org.guzman.despachalo.core.programming.application.port.out;

import java.util.List;

public interface GetEndpointsCostsPort {
    List<EndpointWithCost> getEndpointsCosts(Long originEndpointId, List<Long> endpointIds);
}
