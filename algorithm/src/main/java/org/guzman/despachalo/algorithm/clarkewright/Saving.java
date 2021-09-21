package org.guzman.despachalo.algorithm.clarkewright;

import java.util.ArrayList;

public class Saving {
    public static double[][] calculateSavings(
            double[][] costs,
            Integer originNode,
            ArrayList<Integer> destinationNodes) {
        var savings = new double[destinationNodes.size()+1][destinationNodes.size()+1];

        for (int n1 = 1; n1 <= destinationNodes.size(); n1++) {
            for (int n2 = 1; n2 <= destinationNodes.size(); n2++) {
                if (n1 == n2) {
                    savings[n1][n2] = 0.0;
                    continue;
                }

                savings[n1][n2] = calculateSaving(costs, originNode, n1, n2);
            }
        }

        return savings;
    }

    private static double calculateSaving(double[][] costs, int originNode, int n1, int n2) {
        return costs[originNode][n1] + costs[originNode][n2] - costs[n1][n2];
    }
}
