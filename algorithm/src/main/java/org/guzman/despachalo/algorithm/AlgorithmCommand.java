package org.guzman.despachalo.algorithm;

import lombok.Getter;
import org.guzman.despachalo.algorithm.structures.Matrix;

import java.util.List;

@Getter
public class AlgorithmCommand {
    Matrix<Double> costs;
    List<Double> demand;
    Double commonCapacity;
    Integer totalDestinationNodes;

    public AlgorithmCommand(Matrix<Double> costs, List<Double> demand, Double commonCapacity) {
        this.costs = costs;
        this.demand = demand;
        this.commonCapacity = commonCapacity;
        this.totalDestinationNodes = demand.size() - 1;
    }
}
