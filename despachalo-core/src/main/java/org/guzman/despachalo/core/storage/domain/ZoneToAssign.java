package org.guzman.despachalo.core.storage.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ZoneToAssign {
    private Long id;
    private List<Long> itemsAssigned;
}
