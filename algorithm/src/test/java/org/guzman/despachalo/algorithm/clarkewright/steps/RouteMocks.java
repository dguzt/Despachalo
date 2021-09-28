package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.Route;

import java.util.HashSet;
import java.util.Set;

public class RouteMocks {

    public static Set<Route> emptyRoutesSet() {
        return new HashSet<>();
    }

    public static Set<Route> withOneRoute(Route route) {
        var routes = emptyRoutesSet();
        routes.add(route);
        return routes;
    }
}
