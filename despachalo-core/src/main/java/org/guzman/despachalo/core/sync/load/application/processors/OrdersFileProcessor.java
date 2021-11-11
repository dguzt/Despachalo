package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.sync.load.application.dataframe.EmptyFilter;
import org.guzman.despachalo.core.sync.load.application.dataframe.UniqueFilter;
import org.guzman.despachalo.core.sync.load.application.port.out.GetMappedDestinationPointIdsPort;
import org.guzman.despachalo.core.sync.load.application.port.out.GetMappedProductIdsPort;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterOrdersPort;
import org.guzman.despachalo.core.sync.load.domain.OrderToInsert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.guzman.despachalo.core.sync.load.application.identifier.OrderColumns.*;

@Service
@RequiredArgsConstructor
public class OrdersFileProcessor implements FileProcessor {
    private final EmptyFilter emptyFilter;
    private final UniqueFilter uniqueFilter;
    private final RegisterOrdersPort ordersPort;
    private final GetMappedDestinationPointIdsPort destinationPointIdsPort;
    private final GetMappedProductIdsPort productIdsPort;

    public Integer process(DataFrame<Object> dataFrame) {
        var noEmpties = filterEmpties(dataFrame);
        var uniqueOrders =  uniqueFilter.filterUnique(noEmpties, ORDER_CODE, PRODUCT_CODE);

        var orders = mapToOrders(uniqueOrders);
        ordersPort.registerOrders(orders);

        var size = orders.stream()
                .map(OrderToInsert::getLines)
                .map(List::size)
                .reduce(Integer::sum);

        return size.orElse(0);
    }

    private DataFrame<Object> filterEmpties(DataFrame<Object> destPointsDF) {
        return destPointsDF.select(emptyFilter.filterNoEmptyStrings(ORDER_CODE))
                .select(emptyFilter.filterNoEmptyStrings(DESTINATION_POINT_CODE))
                .select(emptyFilter.filterNoEmptyStrings(PRODUCT_CODE))
                .select(emptyFilter.filterNoEmptyLongs(REQUESTED_AMOUNT));
    }

    private List<OrderToInsert> mapToOrders(DataFrame<Object> dataFrame) {
        var destPointCodes = dataFrame
                .unique(DESTINATION_POINT_CODE)
                .col(DESTINATION_POINT_CODE)
                .stream().map(Object::toString)
                .collect(Collectors.toList());
        var destPointsMapped = destinationPointIdsPort.getMappedDestinationPointIds(destPointCodes);

        var productCodes = dataFrame
                .unique(PRODUCT_CODE)
                .col(PRODUCT_CODE)
                .stream().map(Object::toString)
                .collect(Collectors.toList());
        var productsMapped = productIdsPort.getMappedProductIds(productCodes);

        return dataFrame.groupBy(ORDER_CODE)
                .explode()
                .values()
                .stream()
                .map(linesDF -> {
                    var orderCode = linesDF.get(0, ORDER_CODE).toString();
                    var destPointCode = linesDF.get(0, DESTINATION_POINT_CODE).toString();
                    var destPointId = destPointsMapped.getOrDefault(destPointCode, null);

                    if (destPointId == null) {
                        return null;
                    }

                    var lines = new ArrayList<OrderToInsert.OrderLineToInsert>();
                    linesDF.iterator().forEachRemaining(row -> {
                        var productCode = row.get(PRODUCT_CODE).toString();
                        var productId = productsMapped.getOrDefault(productCode, null);

                        if (productId == null) {
                            return;
                        }

                        var amountRequested = (Long) row.get(REQUESTED_AMOUNT);
                        var line = new OrderToInsert.OrderLineToInsert(productId, amountRequested);
                        lines.add(line);
                    });

                    if (lines.isEmpty()) {
                        return null;
                    }

                    return new OrderToInsert(orderCode, destPointId, lines);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
