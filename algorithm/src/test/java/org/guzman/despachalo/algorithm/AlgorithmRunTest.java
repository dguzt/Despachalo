package org.guzman.despachalo.algorithm;

import org.guzman.despachalo.algorithm.clarkewright.ClarkeWrightAlgorithmFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AlgorithmRunTest {

    @Test
    void givenAllInputs_whenExecuteAlgorithm_shouldGenerateRoutes() {
        var costs = Inputs.costs();
        var demand = Inputs.demand();
        var capacity = 23d;

        var command = new AlgorithmCommand(costs, demand, capacity);

        var algorithm = ClarkeWrightAlgorithmFactory.buildAlgorithm();
        var routes = algorithm.execute(command);

        var route1 = new ArrayList<>(routes).get(0);
        var route2 = new ArrayList<>(routes).get(1);

        var res1 = List.of(0, 7, 8, 9, 5, 6, 0);
        var res2 = List.of(0, 4, 3, 1, 2, 0);
         assertTrue(res1.equals(route1.getNodes()) || res2.equals(route1.getNodes()));
         assertTrue(res1.equals(route2.getNodes()) || res2.equals(route2.getNodes()));
    }
}
