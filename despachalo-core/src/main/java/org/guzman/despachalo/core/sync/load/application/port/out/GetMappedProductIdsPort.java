package org.guzman.despachalo.core.sync.load.application.port.out;

import java.util.List;
import java.util.Map;

public interface GetMappedProductIdsPort {
    Map<String, Long> getMappedProductIds(List<String>codes);
}
