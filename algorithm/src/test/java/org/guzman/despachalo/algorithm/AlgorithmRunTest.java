package org.guzman.despachalo.algorithm;

import org.guzman.despachalo.algorithm.clarkewright.ClarkeWrightAlgorithmFactory;
import org.junit.jupiter.api.Test;

class AlgorithmRunTest {

    @Test
    void main() {
        var costs = Inputs.costs();
        var demand = Inputs.demand();
        var capacity = 23.0;

        var command = new AlgorithmCommand(costs, demand, capacity);

        var algorithm = ClarkeWrightAlgorithmFactory.buildAlgorithm();
        var routes = algorithm.execute(command);
        System.out.println(routes.size());
        routes.forEach(r -> System.out.println(r.getNodes()));
    }
}
