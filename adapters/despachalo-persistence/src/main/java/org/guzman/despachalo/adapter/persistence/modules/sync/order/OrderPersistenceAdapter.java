package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.out.ChangeOrderToReadyPort;
import org.guzman.despachalo.core.storage.application.port.out.ConfirmIfAllItemsAreStoredForOrderPort;
import org.guzman.despachalo.core.sync.load.application.port.out.GetMappedOrderIdsPort;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterOrdersPort;
import org.guzman.despachalo.core.sync.load.domain.OrderToInsert;
import org.guzman.despachalo.core.sync.order.application.port.out.GetAllOrdersPort;
import org.guzman.despachalo.core.sync.order.application.port.out.GetPaginatedOrdersPort;
import org.guzman.despachalo.core.sync.order.domain.Order;
import org.guzman.despachalo.core.sync.order.domain.OrderState;
import org.javatuples.Pair;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.guzman.despachalo.core.sync.order.domain.OrderState.READY;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements
        GetPaginatedOrdersPort,
        GetAllOrdersPort,
        ChangeOrderToReadyPort,
        ConfirmIfAllItemsAreStoredForOrderPort,
        RegisterOrdersPort,
        GetMappedOrderIdsPort {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineMapper orderLineMapper;

    @Override
    public void changeOrderToReady(Long orderId) {
        orderRepository.findById(orderId)
                .ifPresent(row -> {
                    row.setState(READY);
                    orderRepository.save(row);
                });
    }

    @Override
    public Boolean confirmIfAllItemsAreStoredForOrder(Long orderId) {
        orderLineRepository.findAllByOrderId(orderId)
                .forEach(line -> {
                    var productId = line.getProductDetailId();
                });
        return null;
    }

    @Override
    public List<Order> getAllOrdersByState(String state) {
        return orderRepository.findAllByStateOrderByCreatedAtDesc(state)
                .stream()
                .map(orderMapper::toOrder)
                .peek(order -> {
                    var res = calculateItemsForOrder(order.getId());
                    order.setToSendUnits(res.getValue0());
                    order.setStoredUnits(res.getValue1());
                })
                .collect(Collectors.toList());
    }

    private Pair<Integer, Integer> calculateItemsForOrder(Long orderId) {
        var lines = orderLineRepository.findAllByOrderId(orderId);
        var toSend = lines.stream()
                .map(OrderLineEntity::getToSendAmount)
                .reduce(0, Integer::sum);
        var stored = lines.stream()
                .map(OrderLineEntity::getStoredAmount)
                .reduce(0, Integer::sum);
        return Pair.with(toSend, stored);
    }

    @Override
    public Paginator<Order> getOrdersPage(Filters filters, String state) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = orderRepository.findAllByStateOrderByCreatedAtDesc(state, pageable);

        var data = page.getContent()
                .stream()
                .map(orderMapper::toOrder)
                .peek(order -> {
                    var res = calculateItemsForOrder(order.getId());
                    order.setToSendUnits(res.getValue0());
                    order.setStoredUnits(res.getValue1());
                })
                .collect(Collectors.toList());

        return Paginator.<Order>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }

    public List<Order> getAllByDispatchId(Long dispatchId) {
        return orderRepository.findAllByDispatchId(dispatchId)
                .stream()
                .map(orderMapper::toOrder)
                .peek(order -> {
                    var res = calculateItemsForOrder(order.getId());
                    order.setToSendUnits(res.getValue0());
                    order.setStoredUnits(res.getValue1());
                })
                .collect(Collectors.toList());
    }

    @Override
    public void registerOrders(List<OrderToInsert> toInsert) {
        var now = LocalDateTime.now();
        var codes = toInsert.stream()
                .map(OrderToInsert::getCode)
                .collect(Collectors.toList());
        var orders = orderRepository.findAllByCodeIn(codes)
                .stream()
                .collect(Collectors.toMap(
                        OrderEntity::getCode,
                        Function.identity()));

        toInsert.forEach(order -> {
            if (orders.containsKey(order.getCode())) {
                return;
            }

            var row = orderMapper.toEntity(order);
            row.setState(OrderState.INCOMPLETE);
            row.setCreatedAt(now);

            var orderId = orderRepository.save(row).getId();

            var lines = order.getLines()
                    .stream()
                    .map(orderLineMapper::toEntity)
                    .peek(r -> r.setOrderId(orderId))
                    .collect(Collectors.toList());

            orderLineRepository.saveAll(lines);
        });
    }

    @Override
    public Map<String, Long> getMappedOrderIds(List<String> codes) {
        return orderRepository.findAllByCodeIn(codes)
                .stream()
                .collect(Collectors.toMap(
                        OrderEntity::getCode,
                        OrderEntity::getId));
    }
}
