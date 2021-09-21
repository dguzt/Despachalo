package org.guzman.despachalo.algorithm.clarkewright;

import org.guzman.despachalo.algorithm.Inputs;
import org.guzman.despachalo.algorithm.RoutingAlgorithm;

public class ClarkeWrightAlgorithm implements RoutingAlgorithm {

    @Override
    public void execute() {
        var costs = Inputs.costs();
        var demand = Inputs.demand();
        var capacity = 23;

        var originNode = 0;
        var totalDestinationNodes = demand.size() - 1;

        var routes = Routing.buildOptimalRoutes(costs, demand, originNode, totalDestinationNodes, capacity);
    }
}
