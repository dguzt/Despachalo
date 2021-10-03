package org.guzman.despachalo.commons.pagination;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Filters {
    private Integer page;
    private Integer pageSize;
    private String search;
}
