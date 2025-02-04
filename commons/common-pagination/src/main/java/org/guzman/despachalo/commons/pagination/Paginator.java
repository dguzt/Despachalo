package org.guzman.despachalo.commons.pagination;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Builder
@Getter @Setter
public class Paginator<T> {
    private List<T> data;
    private Integer page;
    private Integer pageSize;
    private Long total;

    public static <T> Paginator<T> empty() {
        return Paginator.<T>builder()
                .page(0)
                .pageSize(10)
                .total(0L)
                .data(Collections.emptyList())
                .build();
    }
}
