package org.guzman.despachalo.core.sync.load.application.port.out;

import java.util.List;
import java.util.Map;

public interface FindClientIdsByCodesPort {
    Map<String, Long> findClientIdsByCodes(List<String> codes);
}
