package org.guzman.despachalo.core.sync.load.application.port.out;

import java.util.List;
import java.util.Map;

public interface GetMappedDestinationPointIdsPort {
    Map<String, Long> getMappedDestinationPointIds(List<String> codes);
}
