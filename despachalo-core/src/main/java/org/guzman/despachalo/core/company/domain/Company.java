package org.guzman.despachalo.core.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Company {
    private Long id;
    private String ruc;
    private String businessName;
}
