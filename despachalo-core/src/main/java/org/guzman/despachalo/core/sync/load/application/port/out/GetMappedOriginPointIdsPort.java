package org.guzman.despachalo.core.sync.load.application.port.out;

import java.util.List;
import java.util.Map;

public interface GetMappedOriginPointIdsPort {
    Map<String, Long> getMappedOriginPointIds(List<String> codes);
}
