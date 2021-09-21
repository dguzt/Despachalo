package org.guzman.despachalo.algorithm.clarkewright.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Route {
    private ArrayList<Integer> nodes;

    public boolean has(Integer node) {
        return nodes.contains(node);
    }
}
