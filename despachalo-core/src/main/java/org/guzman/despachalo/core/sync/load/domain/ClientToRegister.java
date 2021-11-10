package org.guzman.despachalo.core.sync.load.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientToRegister {
    private String code;
    private String ruc;
    private String businessName;
}
