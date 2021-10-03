package org.guzman.despachalo.core.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.guzman.despachalo.core.common.domain.Location;

@Getter
@AllArgsConstructor
public class DistributionCenter {
    private Long id;
    private String name;
    private Location location;
    private String address;
}
