package org.guzman.despachalo.commons.pagination;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginatorTest {

    @Test
    void givenARequestForDataPage_shouldGeneratePageWithData() {
        var data = List.of(1L, 2L, 3L, 4L);
        var page = Paginator.<Long>builder()
                .page(1)
                .pageSize(10)
                .data(data)
                .total(4L)
                .build();

        assertEquals(1, page.getPage());
        assertEquals(10, page.getPageSize());
        assertEquals(4, page.getData().size());
        assertEquals(4, page.getTotal());
    }

    @Test
    void givenARequestForEmptyPage_shouldGeneratePageWithoutData() {
        var emptyPage = Paginator.empty();
        assertEquals(0, emptyPage.getData().size());
    }
}
