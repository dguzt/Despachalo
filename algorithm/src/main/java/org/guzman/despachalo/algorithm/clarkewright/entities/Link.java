package org.guzman.despachalo.algorithm.clarkewright.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Link {
    private Integer destinationNode1;
    private Integer destinationNode2;
    private Double accumulatedDemand;
}
