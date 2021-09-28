package org.guzman.despachalo.algorithm.clarkewright;

import org.guzman.despachalo.algorithm.RoutingAlgorithm;
import org.guzman.despachalo.algorithm.clarkewright.steps.Linking;
import org.guzman.despachalo.algorithm.clarkewright.steps.Routing;
import org.guzman.despachalo.algorithm.clarkewright.steps.Saving;

public class ClarkeWrightAlgorithmFactory {
    public static RoutingAlgorithm buildAlgorithm() {
        var saving = new Saving();
        var linking = new Linking();
        var routing = new Routing();

        return new ClarkeWrightAlgorithm(saving, linking, routing);
    }
}
