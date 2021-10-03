package org.guzman.despachalo.core.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.common.domain.IdentityDoc;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String names;
    private String lastnames;
    private IdentityDoc doc;
    private String email;
    private Boolean active;

    private Role role;
}
