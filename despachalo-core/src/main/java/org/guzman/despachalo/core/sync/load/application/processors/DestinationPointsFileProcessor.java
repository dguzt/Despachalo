package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.common.domain.Location;
import org.guzman.despachalo.core.sync.load.application.dataframe.EmptyFilter;
import org.guzman.despachalo.core.sync.load.application.dataframe.MapperDataFrame;
import org.guzman.despachalo.core.sync.load.application.dataframe.UniqueFilter;
import org.guzman.despachalo.core.sync.load.application.port.out.FindCentersByGeocodesPort;
import org.guzman.despachalo.core.sync.load.application.port.out.FindClientIdsByCodesPort;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterDestinationPointsPort;
import org.guzman.despachalo.core.sync.load.domain.DestinationPointToRegister;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.guzman.despachalo.core.sync.load.application.identifier.DestinationPointColumns.*;

@Service
@RequiredArgsConstructor
public class DestinationPointsFileProcessor implements FileProcessor {
    private final EmptyFilter emptyFilter;
    private final UniqueFilter uniqueFilter;
    private final MapperDataFrame mapperDataFrame;
    private final RegisterDestinationPointsPort destinationPointsPort;
    private final FindClientIdsByCodesPort clientIdsByCodesPort;
    private final FindCentersByGeocodesPort centersByGeocodesPort;

    private static final String COL_NAME_CLIENT_ID = "id cliente";
    private static final String COL_NAME_CENTER_ID = "id centro";

    private static final Integer CLIENT_ID = GEOCODE + 1;
    private static final Integer CENTER_ID = GEOCODE + 2;

    @Override
    public Integer process(DataFrame<Object> dataFrame) throws IOException {
        var df = dataFrame.convert(COL_TYPES.toArray(Class[]::new));

        var noEmpties = filterEmpties(df);
        var uniqueCodes =  uniqueFilter.filterUnique(noEmpties, DESTINATION_POINT_CODE);

        var clientIdsCompleted = fillWithClientIds(uniqueCodes);
        var centerIdsCompleted = fillWithCenterIds(clientIdsCompleted);
        var noAddedEmpties = filterAddedEmpties(centerIdsCompleted);

        var destinationPoints = mapperDataFrame.mapToObj(noAddedEmpties, mapToDestinationPoint());
        destinationPointsPort.registerDestinationPoints(destinationPoints);
        return destinationPoints.size();
    }

    private DataFrame<Object> filterAddedEmpties(DataFrame<Object> destPointsDF) {
        return destPointsDF
                .select(emptyFilter.filterNoEmptyStrings(CLIENT_ID))
                .select(emptyFilter.filterNoEmptyStrings(CENTER_ID));
    }

    private DataFrame<Object> fillWithCenterIds(DataFrame<Object> dataFrame) {
        var geocodes = dataFrame.col(GEOCODE)
                .stream()
                .map(Object::toString)
                .map(c -> c.substring(0, 4))
                .collect(Collectors.toList());

        var centersMapped = centersByGeocodesPort.findCentersByGeocodes(geocodes);
        return dataFrame.add(COL_NAME_CENTER_ID, mapCenterIds(centersMapped));
    }

    private DataFrame<Object> fillWithClientIds(DataFrame<Object> dataFrame) {
        var codes = dataFrame.col(CLIENT_CODE)
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        var clientsMapped = clientIdsByCodesPort.findClientIdsByCodes(codes);
        return dataFrame.add(COL_NAME_CLIENT_ID, mapClientIds(clientsMapped));
    }

    private DataFrame.Function<List<Object>, Object> mapClientIds(Map<String, Long> clientsMapped) {
        return (row) -> {
            var code = (String) row.get(CLIENT_CODE);
            return clientsMapped.getOrDefault(code, null);
        };
    }

    private DataFrame.Function<List<Object>, Object> mapCenterIds(Map<String, Long> centersMapped) {
        return (row) -> {
            var geocode = row.get(GEOCODE).toString().substring(0, 4);
            return centersMapped.getOrDefault(geocode, null);
        };
    }

    private DataFrame<Object> filterEmpties(DataFrame<Object> destPointsDF) {
        return destPointsDF.select(emptyFilter.filterNoEmptyStrings(DESTINATION_POINT_CODE))
                .select(emptyFilter.filterNoEmptyStrings(CLIENT_CODE))
                .select(emptyFilter.filterNoEmptyStrings(ADDRESS))
                .select(emptyFilter.filterNoEmptyNumbers(LOCATION_LATITUDE))
                .select(emptyFilter.filterNoEmptyNumbers(LOCATION_LONGITUDE))
                .select(emptyFilter.filterNoEmptyStrings(GEOCODE));
    }

    private Function<List<Object>, DestinationPointToRegister> mapToDestinationPoint() {
        return (row) -> DestinationPointToRegister.builder()
                .code(   (String) row.get(DESTINATION_POINT_CODE))
                .address((String) row.get(ADDRESS))
                .geoCode( (String) row.get(GEOCODE))
                .location(new Location((Double) row.get(LOCATION_LATITUDE), (Double) row.get(LOCATION_LONGITUDE)))
                .clientId((Long) row.get(CLIENT_ID))
                .centerId((Long) row.get(CENTER_ID))
                .build();
    }
}
