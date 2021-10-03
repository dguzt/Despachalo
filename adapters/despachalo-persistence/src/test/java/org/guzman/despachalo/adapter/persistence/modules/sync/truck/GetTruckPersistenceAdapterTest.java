package org.guzman.despachalo.adapter.persistence.modules.sync.truck;

import org.guzman.despachalo.core.sync.application.port.out.GetTruckPort;
import org.guzman.despachalo.core.sync.domain.Truck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import({TruckPersistenceAdapter.class})
@ComponentScan(basePackageClasses = {TruckMapper.class})
public class GetTruckPersistenceAdapterTest {

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private GetTruckPort getTruckPort;

    @Test
    @Sql("classpath:sql/sync/get-truck-persistence-adapter-test.sql")
    void whenGetATruck_AndExistsInDB_ShouldReturnIt() {
        var truckId = 1L;
        assertTrue(truckRepository.existsById(truckId));

        var truck = getTruckPort.getTruck(truckId);
        assertThat(truck)
                .isPresent()
                .map(Truck::getId)
                .hasValue(truckId);
    }

    @Test
    void whenGetATruck_AndNotFoundInDB_ShouldReturnEmpty() {
        var truckId = 1L;
        assertFalse(truckRepository.existsById(truckId));

        var truck = getTruckPort.getTruck(truckId);
        assertThat(truck).isEmpty();
    }
}
