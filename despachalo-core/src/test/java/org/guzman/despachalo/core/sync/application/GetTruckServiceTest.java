package org.guzman.despachalo.core.sync.application;

import org.guzman.despachalo.core.sync.vehicle.application.port.out.GetTruckPort;
import org.guzman.despachalo.core.sync.vehicle.domain.Truck;
import org.guzman.despachalo.core.sync.vehicle.application.GetTruckService;
import org.guzman.despachalo.core.sync.vehicle.application.TruckNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTruckServiceTest {

    @Mock
    private GetTruckPort getTruckPort;

    @InjectMocks
    private GetTruckService getTruckService;

    private Optional<Truck> truckFoundOptional() {
        var truck = new Truck(1L, "ABC-123");
        return Optional.of(truck);
    }

    @Test
    void whenLookingForATruck_andExists_shouldReturnIt() {
        var truckId = 1L;
        when(getTruckPort.getTruck(truckId))
                .thenReturn(truckFoundOptional());

        var truck = getTruckService.execute(truckId);
        assertEquals(truckId, truck.getId());
    }

    private Optional<Truck> truckNotFoundOptional() {
        return Optional.empty();
    }

    @Test
    void whenLookingForATruck_andNoExists_shouldThrowsTruckNotFoundException() {
        var truckId = 1L;
        when(getTruckPort.getTruck(any()))
                .thenReturn(truckNotFoundOptional());

        assertThatExceptionOfType(TruckNotFoundException.class)
                .isThrownBy(() -> getTruckService.execute(truckId));
    }
}
