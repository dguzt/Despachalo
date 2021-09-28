package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkingTest {

    @Test
    void givenASizedSavingMatrix_shouldGenerateUniqueLinks_noMatterNodesOrder() {
        var totalDestinationNodes = 9;
        var totalLinksGenerated = (totalDestinationNodes * totalDestinationNodes - totalDestinationNodes) / 2;
        var savings = MatrixMocks.zeroMatrix(totalDestinationNodes + 1);
        var demand = DemandMocks.zeroDemand(totalDestinationNodes + 1);

        var linking = new Linking();
        var links = linking.getLinkOrderedBySaving(savings, demand);

        assertEquals(totalLinksGenerated, links.size());

        var link12 = new Link(1, 2, 0d);
        var link21 = new Link(2, 1, 0d);
        assertEquals(1, links.stream().filter(l -> compareLinks(l, link12)).count());
        assertEquals(1, links.stream().filter(l -> compareLinks(l, link21)).count());
    }

    @Test
    void givenMatrixWithIncrementalSavings_shouldOrderLinksMustMatchWithTheirNodeNumbers() {
        var totalDestinationNodes = 9;
        var savings = MatrixMocks.incrementalMatrix(totalDestinationNodes + 1);
        var demand = DemandMocks.zeroDemand(totalDestinationNodes + 1);

        var firstLink = new Link(9, 8, 0d);
        var lastLink = new Link(1, 2, 0d);

        var linking = new Linking();
        var links = linking.getLinkOrderedBySaving(savings, demand);

        assertTrue(compareLinks(firstLink, links.get(0)));
        assertTrue(compareLinks(lastLink, links.get(links.size()-1)));
    }

    private boolean compareLinks(Link l1, Link l2) {
        var same = l1.getDestinationNode1().equals(l2.getDestinationNode1())
                && l1.getDestinationNode2().equals(l2.getDestinationNode2());
        var sameReverse = l1.getDestinationNode1().equals(l2.getDestinationNode2())
                && l1.getDestinationNode2().equals(l2.getDestinationNode1());

        return same || sameReverse;
    }
}
