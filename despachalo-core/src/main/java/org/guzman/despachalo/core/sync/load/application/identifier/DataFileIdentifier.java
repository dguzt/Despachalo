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
        var columns = dataFrame.columns();
        if (checkIfProduct(Arrays.asList(columns.toArray()))) {
            return DataType.PRODUCT;
        }

        return DataType.CLIENT;
    }

    private boolean checkIfProduct(List<Object> columns) {
        var sameLength = columns.size() == ProductColumns.NAME_COLUMNS.size();
        if (!sameLength) {
            return false;
        }

        for (int i = 0; i < columns.size(); i++) {
            var col = (String) columns.get(i);
            var name = ProductColumns.NAME_COLUMNS.get(i);
            if (!name.equals(col)) {
                return false;
            }
        }

        return true;
    }
}
