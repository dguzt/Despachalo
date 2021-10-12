package org.guzman.despachalo.core.programming.application.port.out;

import java.util.List;

public interface GetEndpointsDemandPort {
    List<EndpointWithDemand> getEndpointsDemandForDispatch(Long dispatchId);
}
