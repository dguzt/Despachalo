package org.guzman.despachalo.algorithm;

import org.guzman.despachalo.algorithm.clarkewright.ClarkeWrightAlgorithm;

public class AlgorithmRun {
    public static void main(String[] args) {
        var costs = Inputs.costs();
        var demand = Inputs.demand();
        var capacity = 23.0;

        var command = new AlgorithmCommand(costs, demand, capacity);

        var algorithm = new ClarkeWrightAlgorithm();
        var routes = algorithm.execute(command);
        System.out.println(routes.size());
        routes.forEach(r -> System.out.println(r.getNodes()));
    }
}
