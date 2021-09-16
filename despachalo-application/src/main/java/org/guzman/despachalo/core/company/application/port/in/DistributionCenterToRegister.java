package org.guzman.despachalo.core.company.application.port.in;

import lombok.Data;
import org.guzman.despachalo.core.common.domain.Location;

@Data
public class DistributionCenterToRegister {
    String name;
    Location location;
    String address;
}
