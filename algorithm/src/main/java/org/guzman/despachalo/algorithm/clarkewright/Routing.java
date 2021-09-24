package org.guzman.despachalo.algorithm.clarkewright;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.guzman.despachalo.algorithm.clarkewright.entities.DestinationNodes;
import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus.*;

public class Routing {
    public static Set<Route> buildOptimalRoutes(
            ArrayList<Double> demand,
            Integer totalDestinationNodes,
            Double capacity,
            ArrayList<Link> orderedLinks) {

        var destinationNodes = new DestinationNodes(totalDestinationNodes);
        return assignLinksToRoutes(orderedLinks, demand, destinationNodes, capacity);
    }

    private static Set<Route> assignLinksToRoutes(
            ArrayList<Link> links,
            ArrayList<Double> demand,
            DestinationNodes destinationNodes,
            Double commonCapacity) {

        var routes = new HashSet<Route>();

        for (var link : links) {
            System.out.printf("%n%n");
            System.out.printf("[ROUTES]: %s%n", routes);
            System.out.printf("[EVALUATE]: %s%n", link);
            if (link.getAccumulatedDemand() > commonCapacity) {
                continue;
            }

            var info = getLinkInfoAccordingToRoutes(link, routes);

            if (info.getStatus1() == INNER || info.getStatus2() == INNER) {
                continue;
            }

            if (info.getStatus1() == NOT_FOUND && info.getStatus2() == NOT_FOUND) {
                var route = Route.fromLink(link);
                routes.add(route);
                destinationNodes.removeFromRoute(route);
                System.out.printf("[1ST -> LINK AS ROUTE]: %s%n", link);
                continue;
            }

            if (info.getStatus1() != NOT_FOUND || info.getStatus2() != NOT_FOUND) {
                var isNode1NotInRoutes = info.getStatus1() == NOT_FOUND;
                var nodeToConsider = isNode1NotInRoutes ? link.getDestinationNode1() : link.getDestinationNode2();

                var routeToInsertIn = isNode1NotInRoutes ? info.getRoute2() : info.getRoute1();
                var statusToInsertIn = isNode1NotInRoutes ? info.getStatus2() : info.getStatus1();

                var newDemand = routeToInsertIn.getAccumulatedDemand() + demand.get(nodeToConsider);

                if (newDemand > commonCapacity) {
                    continue;
                }

                destinationNodes.removeNode(nodeToConsider);
                if (statusToInsertIn == FIRST_EXTREME) {
                    routeToInsertIn.addNodeAsFirst(demand, nodeToConsider);
                    System.out.printf("[2ND -> LINK FIRST EXTREME]: %s%n", link);
                    continue;
                }

                System.out.printf("[2ND -> LINK LAST EXTREME]: %s%n", link);
                routeToInsertIn.addNodeAsLast(demand, nodeToConsider);
                continue;
            }


            var route1 = info.getRoute1();
            var route2 = info.getRoute2();
            if (info.getRoute1().isNot(info.getRoute2())) {
                var newDemand = route1.getAccumulatedDemand() + route2.getAccumulatedDemand();
                if (newDemand > commonCapacity) {
                    continue;
                }
                routes.remove(route1);
                routes.remove(route2);
                var mergedRoute = mergeRoutes(route1, route2, info.status1, info.status2, demand);
                routes.add(mergedRoute);
                System.out.printf("[3RD -> LINK AS MERGE]: %s%n", link);
            }
        }

        destinationNodes.getPendingNodes().forEach(node -> {
            var route = Route.fromNode(node, demand);
            routes.add(route);
        });

        routes.forEach(Route::setOriginNodeAsFirstAndLast);

        return routes;
    }

    private static Route mergeRoutes(Route route1, Route route2, NodeStatus extremeStatus1, NodeStatus extremeStatus2, ArrayList<Double> demand) {
        if (extremeStatus1 == FIRST_EXTREME) route1.reverse();
        if (extremeStatus2 == LAST_EXTREME) route2.reverse();

        route1.merge(route2, demand);
        return route1;
    }

    private static LinkInfo getLinkInfoAccordingToRoutes(Link link, Set<Route> routes) {
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
    private static class LinkInfo {
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
