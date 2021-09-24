package org.guzman.despachalo.algorithm;

import lombok.Getter;
import org.guzman.despachalo.algorithm.helpers.Matrix;

import java.util.ArrayList;

@Getter
public class AlgorithmCommand {
    Matrix<Double> costs;
    ArrayList<Double> demand;
    Double commonCapacity;
    Integer totalDestinationNodes;

    public AlgorithmCommand(Matrix<Double> costs, ArrayList<Double> demand, Double commonCapacity) {
        this.costs = costs;
        this.demand = demand;
        this.commonCapacity = commonCapacity;
        this.totalDestinationNodes = demand.size() - 1;
    }
}
