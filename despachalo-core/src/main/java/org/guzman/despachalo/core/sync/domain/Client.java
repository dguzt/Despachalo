package org.guzman.despachalo.core.sync.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Client {
    private Long id;
    private String code;
    private String ruc;
    private String businessName;
}
