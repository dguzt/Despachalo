package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.DestinationNodes;
import org.junit.jupiter.api.Test;

import static org.guzman.despachalo.algorithm.clarkewright.entities.Constants.ORIGIN_NODE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoutingTest {

    @Test
    void givenPendingNodes_AndNoMoreLinks_shouldCreateRoutesForEachPendingNode() {
        var totalPendingNodes = 5;
        var destinationNodes = new DestinationNodes(totalPendingNodes);
        var routing = new Routing();
        var routes = RouteMocks.emptyRoutesSet();
        var demand = DemandMocks.zeroDemand(totalPendingNodes + 1);

        routing.convertPendingNodesToRoutes(destinationNodes, routes, demand);
        assertEquals(totalPendingNodes, routes.size());
    }

    @Test
    void givenAllRoutesBuilt_shouldAddOriginNodeToEachRoute() {
        var totalPendingNodes = 5;
        var destinationNodes = new DestinationNodes(totalPendingNodes);
        var routing = new Routing();
        var routes = RouteMocks.emptyRoutesSet();
        var demand = DemandMocks.zeroDemand(totalPendingNodes + 1);
        routing.convertPendingNodesToRoutes(destinationNodes, routes, demand);
        routing.setOriginNodeToRoutes(routes);

        routes.forEach(route -> {
            assertEquals(ORIGIN_NODE, route.getNodes().get(0));
            assertEquals(ORIGIN_NODE, route.getNodes().get(route.getNodes().size() - 1));
        });
    }
}
