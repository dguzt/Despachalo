package org.guzman.despachalo.adapter.persistence.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.driver.DriverMapper;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.truck.TruckMapper;
import org.guzman.despachalo.adapter.persistence.modules.route.RouteEntity;
import org.guzman.despachalo.adapter.persistence.modules.route.RouteRepository;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderEntity;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.common.domain.Location;
import org.guzman.despachalo.core.programming.domain.VehicleDetails;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProgrammedVehiclePersistenceAdapter {
    private final ProgrammedVehicleRepository programmedVehicleRepository;
    private final RouteRepository routeRepository;

    private final TruckMapper truckMapper;
    private final DriverMapper driverMapper;

    public List<VehicleDetails> getProgrammedVehiclesForDispatch(Long dispatchId) {
        return programmedVehicleRepository.findAllByDispatchId(dispatchId)
                .stream()
                .map(programmedVehicleEntity -> {
                    var truck = truckMapper.toTruck(programmedVehicleEntity.getTruck());
                    var driver = driverMapper.toDriver(programmedVehicleEntity.getDriver());
                    var orders = routeRepository
                            .findAllByProgrammedVehicleIdOrderByDeliveryOrderAsc(programmedVehicleEntity.getId());

                    var orderIds = orders.stream()
                            .map(RouteEntity::getOrderId)
                            .collect(Collectors.toList());

                    var locations = orders.stream()
                            .map(RouteEntity::getOrder)
                            .map(OrderEntity::getEndPoint)
                            .map(endpoint -> new Location(endpoint.getLatitude(), endpoint.getLongitude()))
                            .collect(Collectors.toList());

                    return new VehicleDetails(truck, driver, orderIds, locations);
                })
                .collect(Collectors.toList());
    }
}
