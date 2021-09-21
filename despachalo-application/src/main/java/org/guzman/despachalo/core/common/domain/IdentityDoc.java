package org.guzman.despachalo.core.common.domain;

import lombok.Data;

@Data
public class IdentityDoc {
    private DocType type;
    private String number;
}
