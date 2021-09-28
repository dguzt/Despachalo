package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.DestinationNodes;
import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Routing04OnlyOneNodeIsExtremeTest {

    @Test
    void givenBothExtremeNodes_shouldBeDiscarded() {
        // data not used because of inner conditional
        var capacity = 100d;
        var demand = DemandMocks.zeroDemand(6);
        var destinationNodes = new DestinationNodes(5);
        var link = new Link(3, 4, 70d);

        // important data to pass as arguments
        var routing = new Routing();
        var info = new Routing.LinkInfo();
        info.setNodeInfo1(NodeStatus.FIRST_EXTREME, null);
        info.setNodeInfo2(NodeStatus.LAST_EXTREME, null);

        var res = routing.onlyOneNodeIsExtreme(info, link, demand, capacity, destinationNodes);
        assertFalse(res);
    }

    @Test
    void givenOnlyOneExtremeNode_andExactDemandForCapacity_andLastExtreme_shouldAppendLinkToRoute() {
        var capacity = 140d;
        var routing = new Routing();
        var route = route();
        var info = linkInfo(route, NodeStatus.LAST_EXTREME);
        var demand = demand();
        var destinationNodes = destinationNodes(route);

        var extremeNode = 4;
        var nodeNotFound = 5;
        var link = new Link(extremeNode, nodeNotFound, 90d);
        var routes = RouteMocks.withOneRoute(route);

        var res = routing.onlyOneNodeIsExtreme(info, link, demand, capacity, destinationNodes);
        assertTrue(res);

        var uniqueRoute = new ArrayList<>(routes).get(0);
        assertEquals(140d, uniqueRoute.getAccumulatedDemand());
        assertEquals(NodeStatus.LAST_EXTREME, uniqueRoute.evaluate(nodeNotFound));
        assertEquals(NodeStatus.INNER, uniqueRoute.evaluate(extremeNode));

        assertFalse(destinationNodes.getPendingNodes().contains(nodeNotFound));
    }

    @Test
    void givenOnlyOneExtremeNode_andExactDemandForCapacity_andFirstExtreme_shouldAppendLinkToRoute() {
        var capacity = 100d;
        var routing = new Routing();
        var route = route();
        var info = linkInfo(route, NodeStatus.FIRST_EXTREME);
        var demand = demand();
        var destinationNodes = destinationNodes(route);

        var nodeNotFound = 1;
        var extremeNode = 2;
        var link = new Link(extremeNode, nodeNotFound, 30d);
        var routes = RouteMocks.withOneRoute(route);

        var res = routing.onlyOneNodeIsExtreme(info, link, demand, capacity, destinationNodes);
        assertTrue(res);

        var uniqueRoute = new ArrayList<>(routes).get(0);
        assertEquals(100d, uniqueRoute.getAccumulatedDemand());
        assertEquals(NodeStatus.FIRST_EXTREME, uniqueRoute.evaluate(nodeNotFound));
        assertEquals(NodeStatus.INNER, uniqueRoute.evaluate(extremeNode));

        assertFalse(destinationNodes.getPendingNodes().contains(nodeNotFound));
    }

    @Test
    void givenOnlyOneExtremeNode_butDemandGreaterCapacity_shouldBeDiscarded() {
        var capacity = 0d;
        var routing = new Routing();
        var route = route();
        var info = linkInfo(route, NodeStatus.FIRST_EXTREME);
        var demand = demand();
        var destinationNodes = destinationNodes(route);

        var extremeNode = 4;
        var nodeNotFound = 5;
        var link = new Link(extremeNode, nodeNotFound, 70d);
        var routes = RouteMocks.withOneRoute(route);

        var res = routing.onlyOneNodeIsExtreme(info, link, demand, capacity, destinationNodes);
        assertTrue(res);

        var uniqueRoute = new ArrayList<>(routes).get(0);
        assertEquals(90, uniqueRoute.getAccumulatedDemand());
        assertEquals(NodeStatus.NOT_FOUND, uniqueRoute.evaluate(nodeNotFound));
        assertTrue(destinationNodes.getPendingNodes().contains(nodeNotFound));
    }

    private Route route() {
        var nodes = new ArrayList<Integer>();
        nodes.add(2);
        nodes.add(3);
        nodes.add(4);
        return new Route(nodes, 90d);
    }

    private DestinationNodes destinationNodes(Route route) {
        var destinationNodes = new DestinationNodes(6);
        destinationNodes.removeFromRoute(route);
        return destinationNodes;
    }

    private Routing.LinkInfo linkInfo(Route route, NodeStatus status1) {
        var info = new Routing.LinkInfo();
        info.setNodeInfo1(status1, route);
        info.setNodeInfo2(NodeStatus.NOT_FOUND, null);
        return info;
    }

    private List<Double> demand() {
        return List.of(0d, 10d, 20d, 30d, 40d, 50d, 60d);
    }
}
