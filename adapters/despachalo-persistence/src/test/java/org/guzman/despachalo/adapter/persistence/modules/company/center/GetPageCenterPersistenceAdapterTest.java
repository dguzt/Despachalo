package org.guzman.despachalo.adapter.persistence.modules.company.center;

import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.core.company.application.port.out.GetPaginatedDistributionCentersPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import({DistributionCenterPersistenceAdapter.class})
@ComponentScan(basePackageClasses = {DistributionCenterMapper.class})
public class GetPageCenterPersistenceAdapterTest {

    @Autowired
    private GetPaginatedDistributionCentersPort getPaginatedDistributionCentersPort;

    private Filters filters() {
        return Filters.builder()
                .page(0)
                .pageSize(10)
                .search("")
                .build();
    }

    @Test
    @Sql("classpath:sql/company/insert-2-distribution-centers.sql")
    void whenGetCentersPage_AndExistsTwoInDB_ShouldReturnThem() {
        var filters = filters();
        var centersPage = getPaginatedDistributionCentersPort.getPage(filters);

        assertEquals(2L, centersPage.getTotal());
        assertEquals(filters.getPage(), centersPage.getPage());
        assertEquals(filters.getPageSize(), centersPage.getPageSize());
        assertEquals(2L, centersPage.getData().size());
    }
}
