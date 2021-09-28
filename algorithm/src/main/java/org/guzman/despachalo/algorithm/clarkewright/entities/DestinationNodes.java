package org.guzman.despachalo.algorithm.clarkewright.entities;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class DestinationNodes {
    private final Set<Integer> pendingNodes;

    public DestinationNodes(Integer totalPendingNodes) {
        this.pendingNodes = new HashSet<>();
        for (int i = 1; i <= totalPendingNodes; i++) {
            this.pendingNodes.add(i);
        }
    }

    public void removeFromRoute(Route route) {
        route.getNodes().forEach(this.pendingNodes::remove);
    }

    public void removeNode(Integer destinationNode1) {
        this.pendingNodes.remove(destinationNode1);
    }
}
