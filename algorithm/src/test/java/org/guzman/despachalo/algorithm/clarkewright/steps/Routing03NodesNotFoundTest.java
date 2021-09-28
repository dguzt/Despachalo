package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.DestinationNodes;
import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Routing03NodesNotFoundTest {

    @Test
    void givenNotFoundNodes_shouldConvertLinkToRoute() {
        var routing = new Routing();
        var totalNodes = 10;
        var destinationNodes = new DestinationNodes(totalNodes);

        var info = new Routing.LinkInfo();
        var routes = RouteMocks.emptyRoutesSet();
        var link = new Link(1, 2, 10d);

        var res = routing.nodesNotFound(routes, info, link, destinationNodes);
        assertTrue(res);

        var uniqueRoute = new ArrayList<>(routes).get(0);

        // link converted to route and added to route collection
        assertTrue(uniqueRoute.getNodes().contains(link.getDestinationNode1()));
        assertTrue(uniqueRoute.getNodes().contains(link.getDestinationNode2()));
        assertEquals(link.getAccumulatedDemand(), uniqueRoute.getAccumulatedDemand());

        // now, the nodes are not pending
        assertFalse(destinationNodes.getPendingNodes().contains(link.getDestinationNode1()));
        assertFalse(destinationNodes.getPendingNodes().contains(link.getDestinationNode2()));
    }

    @Test
    void givenAtMostOneFoundNode_shouldDoNothing() {
        var routing = new Routing();
        var totalNodes = 10;
        var destinationNodes = new DestinationNodes(totalNodes);

        var info = new Routing.LinkInfo();
        info.setNodeInfo1(NodeStatus.NOT_FOUND, null);
        info.setNodeInfo2(NodeStatus.FIRST_EXTREME, null);
        var routes = RouteMocks.emptyRoutesSet();
        var link = new Link(1, 2, 10d);

        var res = routing.nodesNotFound(routes, info, link, destinationNodes);
        assertFalse(res);
        assertEquals(0, routes.size());
    }
}
