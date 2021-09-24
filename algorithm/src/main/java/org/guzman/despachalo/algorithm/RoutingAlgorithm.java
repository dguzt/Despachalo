package org.guzman.despachalo.algorithm;

import org.guzman.despachalo.algorithm.clarkewright.entities.Route;

import java.util.Set;

public interface RoutingAlgorithm {
    Set<Route> execute(AlgorithmCommand command);
}
