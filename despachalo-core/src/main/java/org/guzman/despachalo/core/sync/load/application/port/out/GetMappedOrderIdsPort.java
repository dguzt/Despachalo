package org.guzman.despachalo.core.sync.load.application.port.out;

import java.util.List;
import java.util.Map;

public interface GetMappedOrderIdsPort {
    Map<String, Long> getMappedOrderIds(List<String> codes);
}
