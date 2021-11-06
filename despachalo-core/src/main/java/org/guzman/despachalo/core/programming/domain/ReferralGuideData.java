package org.guzman.despachalo.core.programming.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.company.domain.Company;
import org.guzman.despachalo.core.sync.order.domain.Client;
import org.guzman.despachalo.core.sync.points.destination.domain.DestinationPoint;
import org.guzman.despachalo.core.sync.points.origin.domain.OriginPoint;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReferralGuideData {
    private Long id;
    private Company company;
    private Client client;
    private Integer totalWeight;
    private VehicleDetails vehicle;
    private OriginPoint originPoint;
    private DestinationPoint destinationPoint;
    private LocalDateTime issuedAt;
    private LocalDateTime transportedAt;
}
