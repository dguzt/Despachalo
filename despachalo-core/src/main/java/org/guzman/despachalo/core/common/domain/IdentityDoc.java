package org.guzman.despachalo.core.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class IdentityDoc {
    private DocType type;
    private String number;
}
