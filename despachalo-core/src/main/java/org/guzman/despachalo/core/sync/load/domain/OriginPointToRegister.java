package org.guzman.despachalo.core.sync.load.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OriginPointToRegister {
    private String code;
    private String address;
}
