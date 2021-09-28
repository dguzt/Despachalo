package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.structures.Matrix;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Linking {
    public List<Link> getLinkOrderedBySaving(Matrix<Double> savings, List<Double> demand) {
        var size = savings.getSize();
        var links = new ArrayList<Pair<Link, Double>>();
        for (int n1 = 1; n1 < size; n1++) {
            for (int n2 = 1; n2 < n1; n2++) {
                var accumulatedDemand = demand.get(n1) + demand.get(n2);
                var link = new Link(n1, n2, accumulatedDemand);
                links.add(Pair.with(link, savings.get(n1, n2)));
            }
        }

        return links.stream()
                .sorted((p1, p2) -> Double.compare(p2.getValue1(), p1.getValue1()))
                .map(Pair::getValue0)
                .collect(Collectors.toList());
    }
}
