package org.guzman.despachalo.algorithm.clarkewright.steps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guzman.despachalo.algorithm.clarkewright.entities.DestinationNodes;
import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus.*;

public final class Routing {
    public Set<Route> buildOptimalRoutes(
            List<Double> demand,
            Integer totalDestinationNodes,
            Double capacity,
            List<Link> orderedLinks) {

        var destinationNodes = new DestinationNodes(totalDestinationNodes);
        return assignLinksToRoutes(orderedLinks, demand, destinationNodes, capacity);
    }

    Set<Route> assignLinksToRoutes(
            List<Link> links,
            List<Double> demand,
            DestinationNodes destinationNodes,
            Double commonCapacity) {

        var routes = new HashSet<Route>();

        for (var link : links) {
            if (exceedsCapacity(link, commonCapacity)) continue;
            var info = getLinkInfoAccordingToRoutes(link, routes);

            if (existsInnerNodes(info)) continue;
            if (nodesNotFound(routes, info, link, destinationNodes)) continue;
            if (onlyOneNodeIsExtreme(info, link, demand, commonCapacity, destinationNodes)) continue;
            bothNodesAreExtreme(info, commonCapacity, routes, demand);
        }

        convertPendingNodesToRoutes(destinationNodes, routes, demand);
        setOriginNodeToRoutes(routes);

        return routes;
    }

    boolean exceedsCapacity(Link link, Double commonCapacity) {
        return link.getAccumulatedDemand() > commonCapacity;
    }

    boolean existsInnerNodes(LinkInfo info) {
        return info.getStatus1() == INNER || info.getStatus2() == INNER;
    }

    boolean nodesNotFound(Set<Route> routes, LinkInfo info, Link link, DestinationNodes destinationNodes) {
        if (info.getStatus1() == NOT_FOUND && info.getStatus2() == NOT_FOUND) {
            var route = Route.fromLink(link);
            routes.add(route);
            destinationNodes.removeFromRoute(route);
            return true;
        }

        return false;
    }

    boolean onlyOneNodeIsExtreme(LinkInfo info,
                                         Link link,
                                         List<Double> demand,
                                         Double commonCapacity,
                                         DestinationNodes destinationNodes) {
        if (info.getStatus1() == NOT_FOUND || info.getStatus2() == NOT_FOUND) {
            var isNode1NotInRoutes = info.getStatus1() == NOT_FOUND;
            var nodeToConsider = isNode1NotInRoutes ? link.getDestinationNode1() : link.getDestinationNode2();

            var routeToInsertIn = isNode1NotInRoutes ? info.getRoute2() : info.getRoute1();
            var statusToInsertIn = isNode1NotInRoutes ? info.getStatus2() : info.getStatus1();

            var newDemand = routeToInsertIn.getAccumulatedDemand() + demand.get(nodeToConsider);

            if (newDemand > commonCapacity) {
                return true;
            }

            destinationNodes.removeNode(nodeToConsider);
            if (statusToInsertIn == FIRST_EXTREME) {
                routeToInsertIn.addNodeAsFirst(demand, nodeToConsider);
                return true;
            }

            routeToInsertIn.addNodeAsLast(demand, nodeToConsider);
            return true;
        }

        return false;
    }

    void bothNodesAreExtreme(LinkInfo info, Double commonCapacity, Set<Route> routes, List<Double> demand) {
        var route1 = info.getRoute1();
        var route2 = info.getRoute2();
        if (info.getRoute1().isNot(info.getRoute2())) {
            var newDemand = route1.getAccumulatedDemand() + route2.getAccumulatedDemand();
            if (newDemand > commonCapacity) {
                return;
            }
            routes.remove(route1);
            routes.remove(route2);
            var mergedRoute = mergeRoutes(route1, route2, info.status1, info.status2, demand);
            routes.add(mergedRoute);
        }
    }

    void convertPendingNodesToRoutes(DestinationNodes destinationNodes, Set<Route> routes, List<Double> demand) {
        destinationNodes.getPendingNodes().forEach(node -> {
            var route = Route.fromNode(node, demand);
            routes.add(route);
        });
    }

    void setOriginNodeToRoutes(Set<Route> routes) {
        routes.forEach(Route::setOriginNodeAsFirstAndLast);
    }

    Route mergeRoutes(Route route1,
                                     Route route2,
                                     NodeStatus extremeStatus1,
                                     NodeStatus extremeStatus2,
                                     List<Double> demand) {
        if (extremeStatus1 == FIRST_EXTREME) route1.reverse();
        if (extremeStatus2 == LAST_EXTREME) route2.reverse();

        route1.merge(route2, demand);
        return route1;
    }

    LinkInfo getLinkInfoAccordingToRoutes(Link link, Set<Route> routes) {
        var linkInfo = new LinkInfo();
        routes.forEach(route -> {
            var status1 = route.evaluate(link.getDestinationNode1());
            var status2 = route.evaluate(link.getDestinationNode2());

            if (status1 != NOT_FOUND) {
                linkInfo.setNodeInfo1(status1, route);
            }

            if (status2 != NOT_FOUND) {
                linkInfo.setNodeInfo2(status2, route);
            }
        });

        return linkInfo;
    }

    @Getter
    @NoArgsConstructor
    static class LinkInfo {
        private NodeStatus status1 = NOT_FOUND;
        private Route route1;
        private NodeStatus status2 = NOT_FOUND;
        private Route route2;

        public void setNodeInfo1(NodeStatus status, Route route) {
            this.status1 = status;
            this.route1 = route;
        }

        public void setNodeInfo2(NodeStatus status, Route route) {
            this.status2 = status;
            this.route2 = route;
        }
    }
}
