package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.sync.load.application.dataframe.EmptyFilter;
import org.guzman.despachalo.core.sync.load.application.dataframe.MapperDataFrame;
import org.guzman.despachalo.core.sync.load.application.dataframe.UniqueFilter;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterOriginPointsPort;
import org.guzman.despachalo.core.sync.load.domain.OriginPointToRegister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static org.guzman.despachalo.core.sync.load.application.identifier.OriginPointColumns.ADDRESS;
import static org.guzman.despachalo.core.sync.load.application.identifier.OriginPointColumns.ORIGIN_POINT_CODE;

@Service
@RequiredArgsConstructor
public class OriginPointsFileProcessor implements FileProcessor {
    private final EmptyFilter emptyFilter;
    private final UniqueFilter uniqueFilter;
    private final MapperDataFrame mapperDataFrame;
    private final RegisterOriginPointsPort originPointsPort;

    public Integer process(DataFrame<Object> dataFrame) {
        var codeClean = dataFrame.select(emptyFilter.filterNoEmptyStrings(ORIGIN_POINT_CODE));
        var addressClean = codeClean.select(emptyFilter.filterNoEmptyStrings(ADDRESS));

        var uniqueCodes =  uniqueFilter.filterUnique(addressClean, ORIGIN_POINT_CODE);
        var originPoints = mapperDataFrame.mapToObj(uniqueCodes, mapToOriginPoint());
        originPointsPort.registerOriginPoints(originPoints);
        return originPoints.size();
    }

    private Function<List<Object>, OriginPointToRegister> mapToOriginPoint() {
        return (row) -> OriginPointToRegister.builder()
                .code((String) row.get(ORIGIN_POINT_CODE))
                .address((String) row.get(ADDRESS))
                .build();
    }
}
