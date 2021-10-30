package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import org.guzman.despachalo.core.sync.load.application.identifier.ProductColumns;
import org.springframework.stereotype.Service;

@Service
public class ClientsFileProcessor implements FileProcessor {

    private void checkUniqueCodes(DataFrame<Object> dataFrame) {
        var uniques = dataFrame.unique(ProductColumns.PRODUCT_CODE);
    }

    @Override
    public void process(DataFrame<Object> dataFrame){
        checkUniqueCodes(dataFrame);
    }
}
