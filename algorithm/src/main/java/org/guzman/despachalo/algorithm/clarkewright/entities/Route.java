package org.guzman.despachalo.algorithm.clarkewright.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.guzman.despachalo.algorithm.clarkewright.entities.Constants.ORIGIN_NODE;

@Getter
@AllArgsConstructor
public class Route {
    private List<Integer> nodes;
    private Double accumulatedDemand;

    public NodeStatus evaluate(Integer node) {
        var first = nodes.get(0);
        if (Objects.equals(first, node)) {
            return NodeStatus.FIRST_EXTREME;
        }

        var last = nodes.get(nodes.size() - 1);
        if (Objects.equals(last, node)) {
            return NodeStatus.LAST_EXTREME;
        }

        if (nodes.contains(node)) {
            return NodeStatus.INNER;
        }

        return NodeStatus.NOT_FOUND;
    }

    public static Route fromLink(Link link) {
        var nodes = new ArrayList<Integer>();
        nodes.add(link.getDestinationNode1());
        nodes.add(link.getDestinationNode2());

        return new Route(nodes, link.getAccumulatedDemand());
    }

    public static Route fromNode(Integer node, List<Double> demand) {
        var accumulatedDemand = demand.get(node);
        var nodes = new ArrayList<Integer>();
        nodes.add(node);

        return new Route(nodes, accumulatedDemand);
    }

    public boolean isNot(Route route) {
        return this != route;
    }

    public void merge(Route anotherRoute) {
        this.nodes.addAll(anotherRoute.getNodes());
        this.accumulatedDemand += anotherRoute.accumulatedDemand;
    }

    public void reverse() {
        Collections.reverse(this.nodes);
    }

    public void addNodeAsLast(List<Double> demand, Integer node) {
        this.nodes.add(node);
        this.accumulatedDemand += demand.get(node);
    }

    public void addNodeAsFirst(List<Double> demand, Integer node) {
        this.nodes.add(0, node);
        this.accumulatedDemand += demand.get(node);
    }

    public void setOriginNodeAsFirstAndLast() {
        this.nodes.add(0, ORIGIN_NODE);
        this.nodes.add(ORIGIN_NODE);
    }
}
