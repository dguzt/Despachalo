package org.guzman.despachalo.adapter.persistence.modules.programming;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.company.center.DistributionCenterEntity;
import org.guzman.despachalo.adapter.persistence.modules.company.user.UserEntity;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.DispatchEntity;
import org.guzman.despachalo.adapter.persistence.modules.dispatch.DispatchRepository;
import org.guzman.despachalo.adapter.persistence.modules.route.DistanceRepository;
import org.guzman.despachalo.adapter.persistence.modules.route.RouteEntity;
import org.guzman.despachalo.adapter.persistence.modules.route.RouteRepository;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderLineEntity;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderLineRepository;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.core.programming.application.port.out.*;
import org.guzman.despachalo.core.programming.domain.RouteRequestState;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class RoutingPersistenceAdapter implements
        GetCenterWithEndpointCostsPort,
        GetEndpointsCostsPort,
        GetEndpointsDemandPort,
        RegisterRoutesPort,
        ChangeStateToDoneForRoutingPort {

    private final OrderRepository orderRepository;
    private final DispatchRepository dispatchRepository;
    private final OrderLineRepository orderLineRepository;
    private final DistanceRepository distanceRepository;
    private final RouteRepository routeRepository;
    private final ProgrammedVehicleRepository programmedVehicleRepository;

    @Override
    public CenterWithCosts getCenterWithEndpointCosts(Long dispatchId, List<Long> endpointIds) {
        var centerOptional = dispatchRepository.findById(dispatchId)
                .map(DispatchEntity::getAnalyst)
                .map(UserEntity::getCenter)
                .map(DistributionCenterEntity::getId);

        if (centerOptional.isEmpty()) {
            throw new RuntimeException("Center not found to get endpoint costs, with dispatch id: " + dispatchId);
        }

        var centerId = centerOptional.get();
        var endpointWithCosts = distanceRepository.findAllByCenterNodeIdAndInputNodeIdIn(centerId, endpointIds)
                .stream()
                .map(distanceEntity -> new EndpointWithCost(distanceEntity.getInputNodeId(), distanceEntity.getCost()))
                .collect(Collectors.toList());

        return new CenterWithCosts(centerId, endpointWithCosts);
    }

    @Override
    public List<EndpointWithCost> getEndpointsCosts(Long originEndpointId, List<Long> endpointIds) {
        return distanceRepository.findAllByOutputNodeIdAndInputNodeIdIn(originEndpointId, endpointIds)
                .stream()
                .map(distanceEntity -> new EndpointWithCost(distanceEntity.getInputNodeId(), distanceEntity.getCost()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EndpointWithDemand> getEndpointsDemandForDispatch(Long dispatchId) {
        return orderRepository.findAllByDispatchId(dispatchId)
                .stream()
                .map(order -> {

                    var orderId = order.getId();
                    var endpointId = order.getStoreId();
                    var demand = orderLineRepository.findAllByOrderId(orderId)
                            .stream()
                            .map(OrderLineEntity::getToSendAmount)
                            .reduce(0, Integer::sum);

                    return new EndpointWithDemand(orderId, endpointId, demand);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void registerRoutes(Set<List<RouteToRegister>> toRegister) {
        toRegister.forEach(routes -> {
            var dispatchId = routes.get(0).getDispatchId();
            var programmedRow = new ProgrammedVehicleEntity();

            programmedRow.setDispatchId(dispatchId);
            var programmedId = programmedVehicleRepository.save(programmedRow).getId();

            var routeRows = routes.stream()
                    .map(route -> {
                        var routeRow = new RouteEntity();
                        routeRow.setOrderId(route.getOrderId());
                        routeRow.setProgrammedVehicleId(programmedId);
                        routeRow.setDeliveryOrder(route.getDeliveryOrder());
                        return routeRow;
                    })
                    .collect(Collectors.toList());
            routeRepository.saveAll(routeRows);
        });
    }

    @Override
    public void changeStateToDoneForRouting(Long dispatchId) {
        dispatchRepository.findById(dispatchId)
                .ifPresent(dispatchEntity -> {
                    dispatchEntity.setRouteRequestState(RouteRequestState.GENERATED);
                    dispatchRepository.save(dispatchEntity);
                });
    }
}
