package org.guzman.despachalo.core.sync.load.domain;

import lombok.Builder;
import lombok.Getter;
import org.guzman.despachalo.core.common.domain.Location;

@Getter
@Builder
public class DestinationPointToRegister {
    private String code;
    private String address;
    private Location location;
    private String geoCode;

    private Long clientId;
    private Long centerId;
}
