package org.guzman.despachalo.algorithm.clarkewright;

import java.util.ArrayList;

public class Nodes {
    public static ArrayList<Integer> destinationNodes(int totalPendingNodes) {
        var pending = new ArrayList<Integer>();

        for (int i = 1; i <= totalPendingNodes; i++) {
            pending.add(i);
        }

        return pending;
    }
}
