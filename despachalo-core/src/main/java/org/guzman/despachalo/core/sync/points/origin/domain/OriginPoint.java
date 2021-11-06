package org.guzman.despachalo.core.sync.points.origin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OriginPoint {
    private Long id;
    private String address;
    private String geoCode;
}
