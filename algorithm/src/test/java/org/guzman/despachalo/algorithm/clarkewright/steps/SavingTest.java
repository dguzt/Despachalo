package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.structures.Matrix;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SavingTest {

    @Test
    void calculateSavingsAccordingToSavingFunction() {
        var totalDestinationNodes = 3;
        var n01 = 2d;
        var n02 = 2d;
        var n03 = 4d;
        var n12 = 2d;
        var n23 = 2d;
        var n13 = 1d;
        var costs = new Matrix<Double>();
        costs.addRow(List.of(0.0, n01, n02, n03));
        costs.addRow(List.of(n01, 0.0, n12, n13));
        costs.addRow(List.of(n02, n12, 0.0, n23));
        costs.addRow(List.of(n03, n13, n23, 0.0));

        var saving = new Saving();
        var savings = saving.calculateSavings(costs, totalDestinationNodes);

        var expected12 = n01 + n02 - n12;
        assertEquals(expected12, savings.get(1, 2));
        assertEquals(expected12, savings.get(2, 1));

        var expected23 = n02 + n03 - n23;
        assertEquals(expected23, savings.get(2, 3));
        assertEquals(expected23, savings.get(3, 2));

        var expected13 = n01 + n03 - n13;
        assertEquals(expected13, savings.get(1, 3));
        assertEquals(expected13, savings.get(3, 1));
    }
}
