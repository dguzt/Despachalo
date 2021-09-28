package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.DestinationNodes;

public class DestinationNodesMocks {
    DestinationNodes destinationNodes(Integer totalNodes) {
        return new DestinationNodes(totalNodes);
    }
}
