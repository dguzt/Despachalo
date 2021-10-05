package org.guzman.despachalo.core.storage.application.port.out;

import org.guzman.despachalo.core.storage.domain.ZoneToAssign;

import java.util.List;

public interface GetAreasByOrderItemsPort {
    List<ZoneToAssign> getAreasByOrderIds(List<Long> orderIds);
}
