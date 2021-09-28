package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Routing05BothNodesAreExtremeTest {

    @Test
    void givenBothExtremeNodes_andFromSameRoute_shouldDiscarded() {
        var routing = new Routing();

        var sameRoute = new Route(Collections.emptyList(), 0d);
        var info = new Routing.LinkInfo();
        info.setNodeInfo1(NodeStatus.LAST_EXTREME, sameRoute);
        info.setNodeInfo2(NodeStatus.FIRST_EXTREME, sameRoute);

        var routes = RouteMocks.emptyRoutesSet();
        routes.add(sameRoute);

        routing.bothNodesAreExtreme(info, 0d, routes);

        // route involved was not removed
        assertTrue(routes.contains(sameRoute));
    }

    @Test
    void givenBothExtremeNodes_andFromDiffRoute_shouldMergeRoutes() {
        var routing = new Routing();
        var capacity = 210d;
        var route1 = route1();
        var route2 = route2();
        var routes = routes(route1, route2);

        var link = new Link(3, 4, 70d);
        var info = new Routing.LinkInfo();
        info.setNodeInfo1(NodeStatus.LAST_EXTREME, route1);
        info.setNodeInfo2(NodeStatus.FIRST_EXTREME, route2);
        var expectedTotalNodes = route1.getNodes().size() + route2.getNodes().size();
        var expectedTotalDemand = route1.getAccumulatedDemand() + route2.getAccumulatedDemand();

        routing.bothNodesAreExtreme(info, capacity, routes);

        var mergedRoute = new ArrayList<>(routes).get(0);
        assertEquals(expectedTotalNodes, mergedRoute.getNodes().size());
        assertEquals(NodeStatus.INNER, mergedRoute.evaluate(link.getDestinationNode1()));
        assertEquals(NodeStatus.INNER, mergedRoute.evaluate(link.getDestinationNode2()));
        assertEquals(expectedTotalDemand, mergedRoute.getAccumulatedDemand());
    }

    private Route route1() {
        var nodes = new ArrayList<Integer>();
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);
        return new Route(nodes, 60d);
    }

    private Route route2() {
        var nodes = new ArrayList<Integer>();
        nodes.add(4);
        nodes.add(5);
        nodes.add(6);
        return new Route(nodes, 150d);
    }

    private Set<Route> routes(Route route1, Route route2) {
        var routes = new HashSet<Route>();
        routes.add(route1);
        routes.add(route2);
        return routes;
    }
}
