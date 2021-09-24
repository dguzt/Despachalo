package org.guzman.despachalo.algorithm.clarkewright.factories;

import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.guzman.despachalo.algorithm.helpers.Matrix;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LinkFactory {
    public static ArrayList<Link> getLinkOrderedBySaving(Matrix<Double> savings, ArrayList<Double> demand) {
        var size = savings.getSize();
        var links = new ArrayList<Pair<Link, Double>>();
        for (int n1 = 1; n1 < size; n1++) {
            for (int n2 = 1; n2 < n1; n2++) {
                var accumulatedDemand = demand.get(n1) + demand.get(n2);
                var link = new Link(n1, n2, accumulatedDemand);
                links.add(Pair.with(link, savings.get(n1, n2)));
            }
        }

        return (ArrayList<Link>) links.stream()
                .sorted((p1, p2) -> Double.compare(p2.getValue1(), p1.getValue1()))
                .map(Pair::getValue0)
                .collect(Collectors.toList());
    }
}
