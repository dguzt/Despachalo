package org.guzman.despachalo.core.storage.application.port.in;

import org.guzman.despachalo.core.storage.domain.Item;
import org.guzman.despachalo.core.storage.domain.ZoneToAssign;

import java.util.List;

public interface AssignAreasToItemsUseCase {
    List<ZoneToAssign> execute(List<Item> items);
}
