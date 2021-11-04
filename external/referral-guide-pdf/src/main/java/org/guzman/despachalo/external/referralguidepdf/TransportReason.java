package org.guzman.despachalo.external.referralguidepdf;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransportReason {
    SALE("VENTA"),
    OTHER("OTROS MOTIVOS");

    private final String value;
}
