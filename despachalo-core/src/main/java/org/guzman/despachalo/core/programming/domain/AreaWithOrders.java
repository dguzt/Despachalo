package org.guzman.despachalo.core.programming.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.storage.domain.Area;

import java.util.List;

@Getter
@AllArgsConstructor
public class AreaWithOrders {
    private Area area;
    private List<Long> orderIds;
}
