package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.Link;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Routing01ExceedsCapacityTest {

    @Test
    void givenLinkWithDemandLowerThanCapacity_shouldNotBeDiscarded() {
        var capacity = 20d;
        var link = new Link(1, 2, 10d);

        var routing = new Routing();
        assertFalse(routing.exceedsCapacity(link, capacity));
    }

    @Test
    void givenLinkWithDemandGraterThanCapacity_shouldBeDiscarded() {
        var capacity = 10d;
        var link = new Link(1, 2, 20d);

        var routing = new Routing();
        assertTrue(routing.exceedsCapacity(link, capacity));
    }
}
