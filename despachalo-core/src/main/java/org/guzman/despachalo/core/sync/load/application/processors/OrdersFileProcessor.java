package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import org.guzman.despachalo.core.sync.load.application.identifier.ProductColumns;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class OrdersFileProcessor implements FileProcessor {
    private void checkUniqueCodes(DataFrame<Object> dataFrame) {
        var uniques = dataFrame.unique(ProductColumns.PRODUCT_CODE);
    }

    @Override
    public void process(DataFrame<Object> dataFrame){
        checkUniqueCodes(dataFrame);
    }
}
