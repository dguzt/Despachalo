package org.guzman.despachalo.adapter.persistence.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.driver.DriverEntity;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.driver.DriverMapper;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.driver.DriverRepository;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.truck.TruckEntity;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.truck.TruckMapper;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.truck.TruckRepository;
import org.guzman.despachalo.adapter.persistence.modules.route.RouteEntity;
import org.guzman.despachalo.adapter.persistence.modules.route.RouteRepository;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderEntity;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.common.domain.Location;
import org.guzman.despachalo.core.programming.application.port.in.AdditionalVehicleData;
import org.guzman.despachalo.core.programming.application.port.out.RegisterPlateAndConductorPort;
import org.guzman.despachalo.core.programming.domain.VehicleDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProgrammedVehiclePersistenceAdapter implements RegisterPlateAndConductorPort {
    private final ProgrammedVehicleRepository programmedVehicleRepository;
    private final DriverRepository driverRepository;
    private final TruckRepository truckRepository;
    private final RouteRepository routeRepository;

    private final TruckMapper truckMapper;
    private final DriverMapper driverMapper;

    public List<VehicleDetails> getProgrammedVehiclesForDispatch(Long dispatchId) {
        return programmedVehicleRepository.findAllByDispatchId(dispatchId)
                .stream()
                .map(programmedVehicleEntity -> {
                    var programmedVehicleId = programmedVehicleEntity.getId();
                    var truck = truckMapper.toTruck(programmedVehicleEntity.getTruck());
                    var driver = driverMapper.toDriver(programmedVehicleEntity.getDriver());
                    var orders = routeRepository
                            .findAllByProgrammedVehicleIdOrderByDeliveryOrderAsc(programmedVehicleId);

                    var orderIds = orders.stream()
                            .map(RouteEntity::getOrderId)
                            .collect(Collectors.toList());

                    var locations = orders.stream()
                            .map(RouteEntity::getOrder)
                            .map(OrderEntity::getEndPoint)
                            .map(endpoint -> new Location(endpoint.getLatitude(), endpoint.getLongitude()))
                            .collect(Collectors.toList());

                    return new VehicleDetails(programmedVehicleId, truck, driver, orderIds, locations);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void registerPlateAndConductor(Long dispatchId, Long programmedVehicleId, AdditionalVehicleData vehicleData) {
        var now = LocalDateTime.now();
        var row = programmedVehicleRepository.findById(programmedVehicleId)
                .orElseThrow(() -> new RuntimeException("Programmed vehicle not found"));

        var vehicle = new TruckEntity();
        vehicle.setVehiclePlate(vehicleData.getPlate());
        vehicle.setCreatedAt(now);

        var driver = new DriverEntity();
        driver.setDocumentType(vehicleData.getDriver().getType().name());
        driver.setDocumentNumber(vehicleData.getDriver().getNumber());
        driver.setCreatedAt(now);

        var vehicleId = truckRepository.save(vehicle).getId();
        var driverId = driverRepository.save(driver).getId();

        row.setVehicleId(vehicleId);
        row.setDriverId(driverId);
        programmedVehicleRepository.save(row);
    }
}
