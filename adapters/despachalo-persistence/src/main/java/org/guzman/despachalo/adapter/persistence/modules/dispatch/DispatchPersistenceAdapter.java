package org.guzman.despachalo.adapter.persistence.modules.dispatch;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.programming.ProgrammedVehiclePersistenceAdapter;
import org.guzman.despachalo.adapter.persistence.modules.storage.area.StoredItemPersistenceAdapter;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderEntity;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderPersistenceAdapter;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.application.port.in.DispatchToRegister;
import org.guzman.despachalo.core.programming.application.port.out.*;
import org.guzman.despachalo.core.programming.domain.*;
import org.guzman.despachalo.core.sync.order.domain.Order;
import org.guzman.despachalo.core.sync.order.domain.OrderState;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DispatchPersistenceAdapter implements
        GetPaginatedDispatchesPort,
        ProgramDispatchPort,
        GetDispatchDetailsPort,
        GetNextDispatchRoutingGenerationPort,
        ConfirmDispatchPort,
        GetDispatchAreasPort {

    private final ProgrammedVehiclePersistenceAdapter programmedVehiclePersistenceAdapter;
    private final OrderPersistenceAdapter orderPersistenceAdapter;
    private final OrderRepository orderRepository;
    private final DispatchRepository dispatchRepository;
    private final DispatchMapper dispatchMapper;
    private final StoredItemPersistenceAdapter storedItemPersistenceAdapter;

    @Override
    public Paginator<Dispatch> getPage(Filters filters, String state) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = dispatchRepository.findAllByStateOrderByDispatchAtDesc(state, pageable);

        var data = page.getContent()
                .stream()
                .map(dispatchMapper::toDispatch)
                .collect(Collectors.toList());

        return Paginator.<Dispatch>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }

    @Override
    public Dispatch programDispatch(DispatchToRegister toRegister, Long analystId) {
        var row = dispatchMapper.toEntity(toRegister);

        row.setAnalystId(analystId);
        row.setCreatedAt(LocalDateTime.now());
        row.setProgrammedOrders(toRegister.getOrderIds().size());
        row.setState(DispatchState.PENDING);
        row.setRouteRequestState(RouteRequestState.PENDING);
        var saved = dispatchRepository.save(row);

        var orders = orderRepository.findAllByIdIn(toRegister.getOrderIds())
                .stream()
                .peek(order -> {
                    order.setState(OrderState.PROGRAMMED);
                    order.setDispatchId(saved.getId());
                    order.setUpdatedAt(LocalDateTime.now());
                })
                .collect(Collectors.toList());

        orderRepository.saveAll(orders);

        return dispatchMapper.toDispatch(saved);
    }

    @Override
    public Optional<DispatchDetails> getDispatchDetails(Long dispatchId) {
        return dispatchRepository.findById(dispatchId)
                .map(row -> {
                    var dispatch = dispatchMapper.toDispatch(row);
                    var orders = orderPersistenceAdapter.getAllByDispatchId(dispatchId);
                    var orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());
                    var storedItems = storedItemPersistenceAdapter.getItemsForOrders(orderIds);
                    var vehicleDetails = programmedVehiclePersistenceAdapter
                            .getProgrammedVehiclesForDispatch(dispatchId);
                    return new DispatchDetails(
                            dispatch,
                            vehicleDetails.size(),
                            row.getRouteRequestState(),
                            orders,
                            vehicleDetails,
                            storedItems);
                });
    }

    @Override
    public Optional<Dispatch> getNextDispatchRoutingGeneration() {
        var alreadyOneExecuting = dispatchRepository.findTopByRouteRequestStateOrderByCreatedAtDesc(RouteRequestState.PROCESSING);
        if (alreadyOneExecuting.isPresent()) {
            return Optional.empty();
        }

        var row = dispatchRepository.findTopByRouteRequestStateOrderByCreatedAtDesc(RouteRequestState.PENDING);
        row.ifPresent(entity -> {
            entity.setRouteRequestState(RouteRequestState.PROCESSING);
            dispatchRepository.save(entity);
        });

        return row.map(dispatchMapper::toDispatch);
    }

    @Override
    public void confirmDispatch(Long dispatchId) {
        var now = LocalDateTime.now();
        dispatchRepository.findById(dispatchId)
                .ifPresent(dispatchEntity -> {
                    dispatchEntity.setState(DispatchState.CONFIRMED);
                    dispatchEntity.setUpdatedAt(now);
                    dispatchRepository.save(dispatchEntity);
                });
    }

    @Override
    public List<AreaWithOrders> getDispatchAreas(Long dispatchId) {
        var orderIds = orderRepository.findAllByDispatchId(dispatchId)
                .stream()
                .map(OrderEntity::getId)
                .collect(Collectors.toList());
        var areasAndOrders = new HashMap<Long, List<Long>>();


        return null;
    }
}
