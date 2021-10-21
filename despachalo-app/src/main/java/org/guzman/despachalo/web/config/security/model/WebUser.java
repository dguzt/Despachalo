package org.guzman.despachalo.web.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.guzman.despachalo.core.company.domain.DistributionCenter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WebUser {
    private Long id;
    private String name;
    private String lastnames;
    private List<Integer> roles;
    private DistributionCenter center;
}
