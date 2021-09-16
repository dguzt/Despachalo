package org.guzman.despachalo.core.company.domain;

import lombok.Value;
import org.guzman.despachalo.core.common.domain.Location;

@Value
public class DistributionCenter {
    Long id;
    Location location;
    String address;
}
