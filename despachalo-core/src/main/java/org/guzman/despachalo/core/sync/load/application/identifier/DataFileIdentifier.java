package org.guzman.despachalo.core.sync.load.application.identifier;

import joinery.DataFrame;
import org.guzman.despachalo.core.sync.load.domain.DataType;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class DataFileIdentifier {

    public String identify(File file) throws IOException {
        var dataFrame = DataFrame.readCsv(file.getPath());
        var columns = Arrays.asList(dataFrame.columns().toArray());

        if (checkIfProduct(columns)) {
            return DataType.PRODUCT;
        }

        if (checkIfClient(columns)) {
            return DataType.CLIENT;
        }

        if (checkIfDestinationPoint(columns)) {
            return DataType.DESTINATION_POINT;
        }

        if (checkIfOrder(columns)) {
            return DataType.ORDER;
        }

        if (checkIfOriginPoint(columns)) {
            return DataType.ORIGIN_POINT;
        }

        if (checkIfCommodity(columns)) {
            return DataType.COMMODITY;
        }

        throw new FileNotIdentifiedException(file.getName());
    }

    private boolean checkIfCommodity(List<Object> columns) {
        return checkIf(columns, CommodityColumns.NAME_COLUMNS);
    }

    private boolean checkIfOriginPoint(List<Object> columns) {
        return checkIf(columns, OriginPointColumns.NAME_COLUMNS);
    }

    private boolean checkIfOrder(List<Object> columns) {
        return checkIf(columns, OrderColumns.NAME_COLUMNS);
    }

    private boolean checkIfDestinationPoint(List<Object> columns) {
        return checkIf(columns, DestinationPointColumns.NAME_COLUMNS);
    }

    private boolean checkIfClient(List<Object> columns) {
        return checkIf(columns, ClientColumns.NAME_COLUMNS);
    }

    private boolean checkIfProduct(List<Object> columns) {
        return checkIf(columns, ProductColumns.NAME_COLUMNS);
    }

    private boolean checkIf(List<Object> columnsToCheck, List<Object> columnsToMap) {
        var sameLength = columnsToCheck.size() == columnsToMap.size();
        if (!sameLength) {
            return false;
        }

        for (int i = 0; i < columnsToCheck.size(); i++) {
            var col = (String) columnsToCheck.get(i);
            var name = columnsToMap.get(i);
            if (!name.equals(col)) {
                return false;
            }
        }

        return true;
    }
}
