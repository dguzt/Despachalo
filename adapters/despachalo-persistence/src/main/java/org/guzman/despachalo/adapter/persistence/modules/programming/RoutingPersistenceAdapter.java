package org.guzman.despachalo.adapter.persistence.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.programming.application.port.out.*;

import java.util.List;
import java.util.Set;

@PersistenceAdapter
@RequiredArgsConstructor
public class RoutingPersistenceAdapter implements
        GetCenterWithEndpointCostsPort,
        GetEndpointsCostsPort,
        GetEndpointsDemandPort,
        RegisterRoutesPort {
    @Override
    public CenterWithCosts getCenterWithEndpointCosts(Long dispatchId, List<Long> endpointIds) {
        return null;
    }

    @Override
    public List<EndpointWithCost> getEndpointsCosts(Long originEndpointId, List<Long> endpointIds) {
        return null;
    }

    @Override
    public List<EndpointWithDemand> getEndpointsDemandForDispatch(Long dispatchId) {
        return null;
    }

    @Override
    public void registerRoutes(Set<List<RouteToRegister>> toRegister) {

    }
}
