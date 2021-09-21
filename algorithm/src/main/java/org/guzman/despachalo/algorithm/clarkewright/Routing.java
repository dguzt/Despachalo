package org.guzman.despachalo.algorithm.clarkewright;

import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Routing {
    public static ArrayList<ArrayList<Integer>> buildOptimalRoutes(
            double[][] costs,
            ArrayList<Double> demand,
            int originNode,
            int totalDestinationNodes,
            int capacity) {

        var routes = new ArrayList<Route>();
        var destinationNodes = Nodes.destinationNodes(totalDestinationNodes);
        var savings = Saving.calculateSavings(costs, originNode, destinationNodes);
        var orderedLinks = getLinkOrderedBySaving(savings);

        return null;
    }

    private static void assignLinksToRoutes(ArrayList<Pair<Link, Double>> linkPairs, ArrayList<Route> routes) {
        for (var pair : linkPairs) {
            var info = getLinkInfoAccordingToRoutes(pair.getValue0(), routes);

        }
    }

    private static double getLinkInfoAccordingToRoutes(Link link, ArrayList<Route> routes) {
        var nodeSel = new ArrayList<Integer>();
        var iRoutes = new int[]{-1, -1};
        var countIn = 0;

        routes.forEach(route -> {
            
        });

        return 0;
    }

    private static void getNodeInfoAccordingToRoute(int node, Route route) {
        var isPresent = route.has(node);
    }

    private static ArrayList<Pair<Link, Double>> getLinkOrderedBySaving(double[][] savings) {
        var links = new ArrayList<Pair<Link, Double>>();
        for (int n1 = 1; n1 < savings.length; n1++) {
            for (int n2 = 1; n2 < savings.length; n2++) {
                if (n1 == n2) continue;

                var link = new Link(n1, n2);
                links.add(Pair.with(link, savings[n1][n2]));
            }
        }

        return (ArrayList<Pair<Link, Double>>) links.stream()
                .sorted((p1, p2) -> Double.compare(p2.getValue1(), p1.getValue1()))
                .collect(Collectors.toList());
    }
}
