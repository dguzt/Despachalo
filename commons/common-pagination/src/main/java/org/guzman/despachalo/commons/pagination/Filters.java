package org.guzman.despachalo.commons.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filters {
    private Integer page;
    private Integer pageSize;
    private String search;
}
