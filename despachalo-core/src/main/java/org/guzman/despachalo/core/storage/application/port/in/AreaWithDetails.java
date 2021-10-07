package org.guzman.despachalo.core.storage.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.storage.domain.Area;
import org.guzman.despachalo.core.storage.domain.Item;

import java.util.List;

@Getter
@AllArgsConstructor
public class AreaWithDetails {
    private Area area;
    private List<Item> storedItems;
}
