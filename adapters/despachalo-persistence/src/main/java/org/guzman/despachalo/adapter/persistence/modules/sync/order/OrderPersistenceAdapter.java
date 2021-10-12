package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.out.ChangeOrderToReadyPort;
import org.guzman.despachalo.core.storage.application.port.out.ConfirmIfAllItemsAreStoredForOrderPort;
import org.guzman.despachalo.core.sync.application.port.out.GetAllOrdersPort;
import org.guzman.despachalo.core.sync.application.port.out.GetPaginatedOrdersPort;
import org.guzman.despachalo.core.sync.domain.Order;
import org.javatuples.Pair;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

import static org.guzman.despachalo.core.sync.domain.OrderState.READY;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements
        GetPaginatedOrdersPort,
        GetAllOrdersPort,
        ChangeOrderToReadyPort,
        ConfirmIfAllItemsAreStoredForOrderPort {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
}
