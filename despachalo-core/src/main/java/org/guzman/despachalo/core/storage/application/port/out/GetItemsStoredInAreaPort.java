package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.core.storage.domain.Item;

import java.util.List;

public interface GetItemsStoredInAreaPort {
    List<Item> getItemsStoredInArea(Long areaId);
}
