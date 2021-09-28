package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.structures.Matrix;

import static org.guzman.despachalo.algorithm.clarkewright.entities.Constants.ORIGIN_NODE;

public final class Saving {

    public Matrix<Double> calculateSavings(
            Matrix<Double> costs,
            Integer totalDestinationNodes) {
        var savings = new Matrix<Double>(totalDestinationNodes + 1);

        for (int n1 = 1; n1 <= totalDestinationNodes; n1++) {
            for (int n2 = 1; n2 <= totalDestinationNodes; n2++) {
                if (n1 == n2) {
                    savings.set(n1, n2, 0.0);
                    continue;
                }

                var saving = calculateSaving(costs, n1, n2);
                savings.set(n1, n2, saving);
            }
        }

        return savings;
    }

    private double calculateSaving(Matrix<Double> costs, int n1, int n2) {
        return costs.get(ORIGIN_NODE, n1) + costs.get(ORIGIN_NODE, n2) - costs.get(n1, n2);
    }
}
