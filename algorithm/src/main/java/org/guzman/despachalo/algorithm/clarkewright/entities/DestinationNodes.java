package org.guzman.despachalo.algorithm.clarkewright.entities;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DestinationNodes {
    private Set<Integer> pendingNodes;

    public DestinationNodes(Integer totalPendingNodes) {
        this.pendingNodes = new HashSet<>();
        for (int i = 1; i <= totalPendingNodes; i++) {
            this.pendingNodes.add(i);
        }
    }

    public void removeFromRoute(Route route) {
        route.getNodes().forEach(node -> this.pendingNodes.remove(node));
    }

    public Integer size() {
        return this.pendingNodes.size();
    }

    public void removeNode(Integer destinationNode1) {
        this.pendingNodes.remove(destinationNode1);
    }
}
