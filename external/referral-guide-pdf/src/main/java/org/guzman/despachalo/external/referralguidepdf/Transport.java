package org.guzman.despachalo.external.referralguidepdf;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Transport {
    private TransportType type;
    private LocalDateTime issuedAt;
    private LocalDateTime transportedAt;
    private TransportReason reason;
    private String totalWeight;
}
