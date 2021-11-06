package org.guzman.despachalo.core.storage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AreaWithItems {
    private Area area;
    private List<Item> items;
}
