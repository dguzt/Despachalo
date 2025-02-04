package org.guzman.despachalo.core.company.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.guzman.despachalo.core.common.domain.Location;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistributionCenterToRegister {
    String name;
    Location location;
    String address;
}
