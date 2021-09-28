package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.DestinationNodes;
import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.junit.jupiter.api.Test;

public class Routing05BothNodesAreExtremeTest {

    @Test
    void givenNotFoundNodes_shouldConvertLinkToRoute() {
        var routing = new Routing();
        var totalNodes = 10;
        var destinationNodes = new DestinationNodes(totalNodes);

        var info = new Routing.LinkInfo();
        var routes = RouteMocks.emptyRoutesSet();
        var link = new Link(1, 2, 10d);

        var res = routing.nodesNotFound(routes, info, link, destinationNodes);
    }
}
