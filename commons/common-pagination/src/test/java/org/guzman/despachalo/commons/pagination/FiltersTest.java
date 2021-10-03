package org.guzman.despachalo.commons.pagination;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FiltersTest {

    @Test
    void givenFiltersInput_buildFiltersForSearch() {
        var filters = Filters.builder()
                .search("Search something")
                .pageSize(10)
                .page(1)
                .build();

        assertEquals("Search something", filters.getSearch());
        assertEquals(1, filters.getPage());
        assertEquals(10, filters.getPageSize());
    }
}
