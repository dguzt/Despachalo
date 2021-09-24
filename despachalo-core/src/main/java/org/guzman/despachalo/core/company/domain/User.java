package org.guzman.despachalo.core.company.domain;

import lombok.Value;
import org.guzman.despachalo.core.common.domain.IdentityDoc;

@Value
public class User {
    Long id;
    String names;
    String lastnames;
    IdentityDoc doc;
    String email;
    Boolean active;

    Role role;
}
