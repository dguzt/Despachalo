package org.guzman.despachalo.core.sync.load.application.port.out;

import java.util.List;
import java.util.Map;

public interface FindCentersByGeocodesPort {
    Map<String, Long> findCentersByGeocodes(List<String> geocodes);
}
