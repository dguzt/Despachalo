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
    private static final Integer COLUMN_ROW = 0;

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
        var colsStatic = CommodityColumns.NAME_COLUMNS_STATIC;
        var colsVariable = CommodityColumns.NAME_COLUMNS_VAR;
        return checkIfIter(columns, colsStatic, colsVariable);
    }

    private boolean checkIfOriginPoint(List<Object> columns) {
        return checkIf(columns, OriginPointColumns.NAME_COLUMNS);
    }

    private boolean checkIfOrder(List<Object> columns) {
        var colsStatic = OrderColumns.NAME_COLUMNS_STATIC;
        var colsVariable = OrderColumns.NAME_COLUMNS_VAR;
        return checkIfIter(columns, colsStatic, colsVariable);
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

    private boolean checkIfIter(List<Object> columnsToCheck, List<Object> columnsStaticToMap, List<Object> columnsVarToMap) {
        var sameLength = columnsToCheck.size() >= (columnsStaticToMap.size() + columnsVarToMap.size());
        if (!sameLength) {
            return false;
        }

        for (int i = 0; i < columnsStaticToMap.size(); i++) {
            var name = (String) columnsStaticToMap.get(i);
            var col = (String) columnsToCheck.get(i);

            if (!name.equals(col)) {
                return false;
            }
        }

        return true;
    }
}
