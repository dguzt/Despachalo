package org.guzman.despachalo.core.sync.points.destination.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DestinationPoint {
    private Long id;
    private String address;
    private String geoCode;
}
