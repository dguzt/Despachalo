package org.guzman.despachalo.core.programming.application;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.algorithm.AlgorithmCommand;
import org.guzman.despachalo.algorithm.clarkewright.ClarkeWrightAlgorithmFactory;
import org.guzman.despachalo.algorithm.clarkewright.entities.Route;
import org.guzman.despachalo.algorithm.structures.Matrix;
import org.guzman.despachalo.commons.hexagonal.UseCase;
import org.guzman.despachalo.core.programming.application.port.in.GenerateRoutesForDispatchUseCase;
import org.guzman.despachalo.core.programming.application.port.out.*;
import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GenerateRoutesForDispatchService implements GenerateRoutesForDispatchUseCase {
    private final GetEndpointsDemandPort endpointsDemandPort;
    private final GetEndpointsCostsPort endpointsCostsPort;
    private final GetCenterWithEndpointCostsPort centerWithEndpointCostsPort;
    private final RegisterRoutesPort routesPort;
    private final ChangeStateToDoneForRoutingPort routingPort;

    @Override
    public void execute(Long dispatchId, Double commonCapacity) {
        var endpointsWithDemand = endpointsDemandPort.getEndpointsDemandForDispatch(dispatchId);
        var mappingEndpoints = mapEndpoints(endpointsWithDemand);
        var demand = generateDemand(mappingEndpoints);
        var pairMatrix = generateCostMatrix(dispatchId, endpointsWithDemand, mappingEndpoints);

        var algorithm = ClarkeWrightAlgorithmFactory.buildAlgorithm();
        var command = new AlgorithmCommand(pairMatrix.getValue1(), demand, commonCapacity);

        var routes = algorithm.execute(command);
        var routesToRegister = generateRoutesToRegister(pairMatrix.getValue0(), dispatchId, routes, mappingEndpoints);
        routesPort.registerRoutes(routesToRegister);
        routingPort.changeStateToDoneForRouting(dispatchId);
    }

    private Set<List<RouteToRegister>> generateRoutesToRegister(
            Long centerId,
            Long dispatchId,
            Set<Route> routes,
            Map<Long, EntryMappingEndpoint> mappingEndpoints) {
        var mappingByNodeId = new HashMap<Integer, EntryMappingEndpoint>();
        mappingEndpoints.forEach((key, value) -> mappingByNodeId.put(value.getPosition(), value));

        return routes.stream()
                .map(route -> {
                    var nodes = route.getNodes();
                    var routesToRegister = new ArrayList<RouteToRegister>();
                    for (int pos = 1; pos < nodes.size()-1; pos++) {
                        var nodeId = nodes.get(pos);
                        var orderId = mappingByNodeId.get(nodeId).getOrderId();
                        var routeToRegister = new RouteToRegister(orderId, dispatchId, pos);
                        routesToRegister.add(routeToRegister);
                    }
                    return routesToRegister;
                })
                .collect(Collectors.toSet());
    }

    private List<Double> generateDemand(Map<Long, EntryMappingEndpoint> mappingEndpoints) {
        var accumulatedDemand = new ArrayList<>(Collections.nCopies(mappingEndpoints.size()+1, 0d));
        mappingEndpoints.forEach((endpointId, entry) -> {
            var index = mappingEndpoints.get(endpointId).getPosition();
            accumulatedDemand.set(index, entry.getDemand());
        });
        return accumulatedDemand;
    }

    private Pair<Long, Matrix<Double>> generateCostMatrix(
            Long dispatchId,
            List<EndpointWithDemand> endpointsWithDemand,
            Map<Long, EntryMappingEndpoint> mappingEndpoints) {

        var matrix = new Matrix<Double>(mappingEndpoints.size() + 1);

        var endpointIds = endpointsWithDemand.stream()
                .map(EndpointWithDemand::getEndpointId)
                .collect(Collectors.toList());

        var centerWithCosts = centerWithEndpointCostsPort
                .getCenterWithEndpointCosts(dispatchId, endpointIds);

        matrix.set(0, 0, 0d);
        centerWithCosts.getEndpointWithCosts()
                .forEach(endWithCost -> {
                    var r = 0;
                    var c = mappingEndpoints.get(endWithCost.getEndpointId()).getPosition();
                    matrix.set(r, c, endWithCost.getCost());
                    matrix.set(c, r, endWithCost.getCost());
                });

        endpointIds.forEach(end -> {
            var r = mappingEndpoints.get(end).getPosition();
            endpointsCostsPort.getEndpointsCosts(end, endpointIds)
                    .forEach(endWithCost -> {
                        var c = mappingEndpoints.get(endWithCost.getEndpointId()).getPosition();
                        matrix.set(r, c, endWithCost.getCost());
                        matrix.set(c, r, endWithCost.getCost());
                    });
        });

        return Pair.with(centerWithCosts.getCenterId(), matrix);
    }

    private Map<Long, EntryMappingEndpoint> mapEndpoints(List<EndpointWithDemand> endpointsWithDemand) {
        var mappingEndpoints = new HashMap<Long, EntryMappingEndpoint>();
        for (int count = 1; count <= endpointsWithDemand.size(); count++) {
            var endpoint = endpointsWithDemand.get(count-1);
            mappingEndpoints.put(endpoint.getEndpointId(), new EntryMappingEndpoint(
                    count,
                    endpoint.getOrderId(),
                    Double.valueOf(endpoint.getDemand())));
        }

        return mappingEndpoints;
    }
}
