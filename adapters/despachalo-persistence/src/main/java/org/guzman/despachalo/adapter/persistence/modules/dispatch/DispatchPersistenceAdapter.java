package org.guzman.despachalo.adapter.persistence.modules.dispatch;

import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.adapter.persistence.modules.sync.order.OrderRepository;
import org.guzman.despachalo.commons.hexagonal.PersistenceAdapter;
import org.guzman.despachalo.commons.pagination.Filters;
import org.guzman.despachalo.commons.pagination.Paginator;
import org.guzman.despachalo.core.programming.application.port.in.DispatchToRegister;
import org.guzman.despachalo.core.programming.application.port.out.GetPaginatedDispatchesPort;
import org.guzman.despachalo.core.programming.application.port.out.ProgramDispatchPort;
import org.guzman.despachalo.core.programming.domain.Dispatch;
import org.guzman.despachalo.core.programming.domain.DispatchState;
import org.guzman.despachalo.core.programming.domain.RouteRequestState;
import org.guzman.despachalo.core.sync.domain.OrderState;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DispatchPersistenceAdapter implements GetPaginatedDispatchesPort, ProgramDispatchPort {
    private final OrderRepository orderRepository;
    private final DispatchRepository dispatchRepository;
    private final DispatchMapper dispatchMapper;

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
                .peek(order -> order.setState(OrderState.PROGRAMMED))
                .collect(Collectors.toList());

        orderRepository.saveAll(orders);

        return dispatchMapper.toDispatch(saved);
    }
}
