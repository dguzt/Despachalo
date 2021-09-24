package org.guzman.despachalo.algorithm.clarkewright.entities;

import lombok.Value;

@Value
public class Link {
    Integer destinationNode1;
    Integer destinationNode2;
    Double accumulatedDemand;

    @Override
    public String toString() {
        return String.format("[(%s,%s) - %s]", destinationNode1, destinationNode2, accumulatedDemand);
    }
}
