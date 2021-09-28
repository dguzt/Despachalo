package org.guzman.despachalo.algorithm.clarkewright;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.algorithm.AlgorithmCommand;
import org.guzman.despachalo.algorithm.RoutingAlgorithm;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;
import org.guzman.despachalo.algorithm.clarkewright.steps.Linking;
import org.guzman.despachalo.algorithm.clarkewright.steps.Routing;
import org.guzman.despachalo.algorithm.clarkewright.steps.Saving;

import java.util.Set;

@RequiredArgsConstructor
public class ClarkeWrightAlgorithm implements RoutingAlgorithm {
    private final Saving saving;
    private final Linking linking;
    private final Routing routing;

    @Override
    public Set<Route> execute(AlgorithmCommand command) {
        var costs = command.getCosts();
        var demand = command.getDemand();
        var capacity = command.getCommonCapacity();

        var totalDestinationNodes = command.getTotalDestinationNodes();

        var savings = saving.calculateSavings(costs, totalDestinationNodes);
        var orderedLinks = linking.getLinkOrderedBySaving(savings, demand);

        return routing.buildOptimalRoutes(demand, totalDestinationNodes, capacity, orderedLinks);
    }
}
