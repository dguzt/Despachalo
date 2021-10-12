package org.guzman.despachalo.core.programming.application.port.out;

import org.guzman.despachalo.core.programming.domain.AreaWithOrders;

import java.util.List;

public interface GetDispatchAreasPort {
    List<AreaWithOrders> getDispatchAreas(Long dispatchId);
}
