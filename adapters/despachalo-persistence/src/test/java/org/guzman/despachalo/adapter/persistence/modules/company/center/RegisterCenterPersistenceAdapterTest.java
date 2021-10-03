package org.guzman.despachalo.adapter.persistence.modules.company.center;

import org.guzman.despachalo.core.common.domain.Location;
import org.guzman.despachalo.core.company.application.port.in.DistributionCenterToRegister;
import org.guzman.despachalo.core.company.application.port.out.RegisterDistributionCenterPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import({DistributionCenterPersistenceAdapter.class})
@ComponentScan(basePackageClasses = {DistributionCenterMapper.class})
public class RegisterCenterPersistenceAdapterTest {

    @Autowired
    private DistributionCenterRepository centerRepository;

    @Autowired
    private RegisterDistributionCenterPort registerDistributionCenterPort;

    private DistributionCenterToRegister center() {
        var location = new Location(0.1d, 0.1d);
        return new DistributionCenterToRegister("Distribution", location, "Address");
    }

    @Test
    void whenRegisterCenter_AndWithValidValues_ShouldBeRegisteredInDB() {
        var centerToRegister = center();
        var center = registerDistributionCenterPort.register(centerToRegister);
        var centerId = center.getId();

        assertEquals("Distribution", center.getName());
        assertEquals("Address", center.getAddress());

        assertNotNull(centerId);
        assertThat(centerRepository.findById(centerId)).isPresent();
    }
}
