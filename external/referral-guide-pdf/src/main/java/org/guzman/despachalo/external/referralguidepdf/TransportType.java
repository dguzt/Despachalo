package org.guzman.despachalo.external.referralguidepdf;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransportType {
    PUBLIC("PUBLICO"),
    PRIVATE("PRIVADO");

    private final String value;
}
