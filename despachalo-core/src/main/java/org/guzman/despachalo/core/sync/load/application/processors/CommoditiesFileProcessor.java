package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.storage.domain.ShipmentState;
import org.guzman.despachalo.core.sync.load.application.dataframe.EmptyFilter;
import org.guzman.despachalo.core.sync.load.application.dataframe.UniqueFilter;
import org.guzman.despachalo.core.sync.load.application.port.out.*;
import org.guzman.despachalo.core.sync.load.domain.CommodityToRegister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.guzman.despachalo.core.sync.load.application.identifier.CommodityColumns.*;

@Service
@RequiredArgsConstructor
public class CommoditiesFileProcessor implements FileProcessor {
    private final EmptyFilter emptyFilter;
    private final UniqueFilter uniqueFilter;
    private final RegisterCommoditiesPort commoditiesPort;
    private final GetMappedOriginPointIdsPort originPointIdsPort;
    private final GetMappedProductIdsPort productIdsPort;
    private final GetMappedOrderIdsPort orderIdsPort;
    private final FindCentersByGeocodesPort centersByGeocodesPort;

    public Integer process(DataFrame<Object> dataFrame) {
        var noEmpties = filterEmpties(dataFrame);
        var uniqueCommodities =  uniqueFilter.filterUnique(noEmpties, ITEM_CODE);

        var commodities = mapToCommodities(uniqueCommodities);
        commoditiesPort.registerCommodities(commodities);

        var size = commodities.stream()
                .map(CommodityToRegister::getItems)
                .map(List::size)
                .reduce(Integer::sum);

        return size.orElse(0);
    }

    private DataFrame<Object> filterEmpties(DataFrame<Object> destPointsDF) {
        return destPointsDF.select(emptyFilter.filterNoEmptyStrings(COMMODITY_CODE))
                .select(emptyFilter.filterNoEmptyStrings(ORIGIN_POINT_CODE))
                .select(emptyFilter.filterNoEmptyStrings(PLATE))
                .select(emptyFilter.filterNoEmptyStrings(DELIVERY_GEOCODE))
                .select(emptyFilter.filterNoEmptyStrings(ARRIVAL_DATE))
                .select(emptyFilter.filterNoEmptyStrings(ITEM_CODE))
                .select(emptyFilter.filterNoEmptyStrings(ITEM_PRODUCT_CODE))
                .select(emptyFilter.filterNoEmptyStrings(ITEM_ORDER_CODE));
    }

    private List<CommodityToRegister> mapToCommodities(DataFrame<Object> dataFrame) {
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        var originPointCodes = uniqueFilter.uniqueCols(dataFrame, ORIGIN_POINT_CODE);
        var productCodes = uniqueFilter.uniqueCols(dataFrame, ITEM_PRODUCT_CODE);
        var orderCodes = uniqueFilter.uniqueCols(dataFrame, ITEM_ORDER_CODE);
        var geocodes = uniqueFilter.uniqueCols(dataFrame, DELIVERY_GEOCODE)
                .stream()
                .map(gc -> gc.substring(0, 4))
                .collect(Collectors.toList());

        var pointsMapped = originPointIdsPort.getMappedOriginPointIds(originPointCodes);
        var productsMapped = productIdsPort.getMappedProductIds(productCodes);
        var ordersMapped = orderIdsPort.getMappedOrderIds(orderCodes);
        var centersMapped = centersByGeocodesPort.findCentersByGeocodes(geocodes);

        return dataFrame.groupBy(COMMODITY_CODE)
                .explode()
                .values()
                .stream()
                .map(linesDF -> {
                    var commodityCode = linesDF.get(0, COMMODITY_CODE).toString();
                    var originPointCode = linesDF.get(0, ORIGIN_POINT_CODE).toString();
                    var plate = linesDF.get(0, PLATE).toString();
                    var geocode = linesDF.get(0, DELIVERY_GEOCODE).toString();
                    var arrivalDate = (Date) linesDF.get(0, ARRIVAL_DATE);

                    var centerId = centersMapped.getOrDefault(geocode.substring(0, 4), null);
                    var originPointId = pointsMapped.getOrDefault(originPointCode, null);
                    if (originPointId == null || centerId == null) {
                        return null;
                    }

                    var items = new ArrayList<CommodityToRegister.ItemToRegister>();
                    linesDF.iterator().forEachRemaining(row -> {
                        var productCode = row.get(ITEM_PRODUCT_CODE).toString();
                        var productId = productsMapped.getOrDefault(productCode, null);

                        var orderCode = row.get(ITEM_ORDER_CODE).toString();
                        var orderId = ordersMapped.getOrDefault(orderCode, null);

                        if (Objects.isNull(orderId) || Objects.isNull(productId)) {
                            return;
                        }

                        var itemCode = row.get(ITEM_CODE).toString();
                        var item = new CommodityToRegister.ItemToRegister(itemCode, productId, orderId);
                        items.add(item);
                    });

                    if (items.isEmpty()) {
                        return null;
                    }

                    return CommodityToRegister.builder()
                            .code(commodityCode)
                            .arrivalDate(convertToLocalDateTime(arrivalDate))
                            .items(items)
                            .plate(plate)
                            .deliveryGeocode(geocode)
                            .centerId(centerId)
                            .originPointId(originPointId)
                            .state(ShipmentState.TO_ARRIVE)
                            .build();
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
    }
}
