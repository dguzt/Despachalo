package org.guzman.despachalo.algorithm.clarkewright;

import org.guzman.despachalo.algorithm.AlgorithmCommand;
import org.guzman.despachalo.algorithm.RoutingAlgorithm;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;

import java.util.Set;

import static org.guzman.despachalo.algorithm.clarkewright.Routing.buildOptimalRoutes;
import static org.guzman.despachalo.algorithm.clarkewright.Saving.calculateSavings;
import static org.guzman.despachalo.algorithm.clarkewright.factories.LinkFactory.getLinkOrderedBySaving;

public class ClarkeWrightAlgorithm implements RoutingAlgorithm {

    @Override
    public Set<Route> execute(AlgorithmCommand command) {
        var costs = command.getCosts();
        var demand = command.getDemand();
        var capacity = command.getCommonCapacity();

        var totalDestinationNodes = command.getTotalDestinationNodes();

        var savings = calculateSavings(costs, totalDestinationNodes);
        var orderedLinks = getLinkOrderedBySaving(savings, demand);

        return buildOptimalRoutes(demand, totalDestinationNodes, capacity, orderedLinks);
    }
}
