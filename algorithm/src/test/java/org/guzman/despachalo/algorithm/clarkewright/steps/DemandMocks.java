package org.guzman.despachalo.algorithm.clarkewright.steps;

import java.util.Collections;
import java.util.List;

public class DemandMocks {
    public static List<Double> zeroDemand(Integer size) {
        return Collections.nCopies(size, 0d);
    }
}
