package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.clarkewright.entities.NodeStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Routing02ExistsInnerNodesTest {

    @Test
    void givenInnerNodes_shouldNotBeDiscarded() {
        var routing = new Routing();

        var infoInner = new Routing.LinkInfo();
        infoInner.setNodeInfo1(NodeStatus.INNER, null);
        infoInner.setNodeInfo2(NodeStatus.INNER, null);
        assertTrue(routing.existsInnerNodes(infoInner));

        var infoMixed = new Routing.LinkInfo();
        infoMixed.setNodeInfo1(NodeStatus.NOT_FOUND, null);
        infoMixed.setNodeInfo2(NodeStatus.INNER, null);
        assertTrue(routing.existsInnerNodes(infoMixed));
    }

    @Test
    void givenLinkWithDemandGraterThanCapacity_shouldBeDiscarded() {
        var routing = new Routing();

        var infoNotFound = new Routing.LinkInfo();
        infoNotFound.setNodeInfo1(NodeStatus.NOT_FOUND, null);
        infoNotFound.setNodeInfo2(NodeStatus.NOT_FOUND, null);
        assertFalse(routing.existsInnerNodes(infoNotFound));

        var infoExtreme = new Routing.LinkInfo();
        infoExtreme.setNodeInfo1(NodeStatus.FIRST_EXTREME, null);
        infoExtreme.setNodeInfo2(NodeStatus.LAST_EXTREME, null);
        assertFalse(routing.existsInnerNodes(infoExtreme));
    }
}
