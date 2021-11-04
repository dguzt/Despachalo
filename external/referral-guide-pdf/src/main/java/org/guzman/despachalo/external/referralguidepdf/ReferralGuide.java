package org.guzman.despachalo.external.referralguidepdf;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReferralGuide {
    private String id;
    private SendingCompany company;
    private TargetClient client;
    private Transport transport;
    private OriginPoint originPoint;
    private DestinationPoint destinationPoint;
    private List<TransportVehicle> vehicles;
}
