package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.storage.commodity.ItemRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.storage.application.port.out.ChangeOrderToReadyPort;
import org.guzman.despachalo.core.storage.application.port.out.ConfirmIfAllItemsAreStoredForOrderPort;
import org.guzman.despachalo.core.sync.application.port.out.GetAllOrdersPort;
import org.guzman.despachalo.core.sync.application.port.out.GetPaginatedOrdersPort;
import org.guzman.despachalo.core.sync.domain.Order;
import org.springframework.data.domain.Page;
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

    private final ItemRepository itemRepository;
    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    // TODO: implement SQL for incomplete order state page
    @Override
    public Paginator<Order> getIncompletePage(Filters filters) {
        return getReadyPage(filters);
    }

    @Override
    public Paginator<Order> getProgrammedPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = orderRepository.findAllByDispatchNotNullOrderByDispatch_DispatchAtDesc(pageable);
        return paginateResults(page, filters);
    }

    // TODO: implement SQL for ready order state page
    @Override
    public Paginator<Order> getReadyPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = orderRepository.findAll(pageable);
        return paginateResults(page, filters);
    }

    private Paginator<Order> paginateResults(Page<OrderEntity> page, Filters filters) {
        var data = page.getContent()
                .stream()
                .map(orderMapper::toOrder)
                .collect(Collectors.toList());

        return Paginator.<Order>builder()
                .page(filters.getPage())
                .pageSize(filters.getPageSize())
                .total(page.getTotalElements())
                .data(data)
                .build();
    }

    // TODO: implement SQL for ready order state list
    @Override
    public List<Order> getAllReady() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrder)
                .collect(Collectors.toList());
    }

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
}
