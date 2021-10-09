package org.guzman.despachalo.adapter.persistence.modules.sync.order;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.sync.application.port.out.GetAllOrdersPort;
import org.guzman.despachalo.core.sync.application.port.out.GetPaginatedOrdersPort;
import org.guzman.despachalo.core.sync.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements GetPaginatedOrdersPort, GetAllOrdersPort {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    // TODO: implement SQL for incomplete order state page
    @Override
    public Paginator<Order> getIncompletePage(Filters filters) {
        return getReadyPage(filters);
    }

    @Override
    public Paginator<Order> getProgrammedPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAllByDispatchNotNullOrderByDispatch_DispatchAtDesc(pageable);
        return paginateResults(page, filters);
    }

    // TODO: implement SQL for ready order state page
    @Override
    public Paginator<Order> getReadyPage(Filters filters) {
        var pageable = PageRequest.of(filters.getPage(), filters.getPageSize());
        var page = repository.findAll(pageable);
        return paginateResults(page, filters);
    }

    private Paginator<Order> paginateResults(Page<OrderEntity> page, Filters filters) {
        var data = page.getContent()
                .stream()
                .map(mapper::toOrder)
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
        return repository.findAll()
                .stream()
                .map(mapper::toOrder)
                .collect(Collectors.toList());
    }
}
