package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static org.guzman.despachalo.core.sync.load.domain.DataType.*;

@Service
@RequiredArgsConstructor
public class DataFileProcessor {
    private final ProductsFileProcessor productsFileProcessor;
    private final ClientsFileProcessor clientsFileProcessor;
    private final OrdersFileProcessor ordersFileProcessor;
    private final OriginPointsFileProcessor originPointsFileProcessor;
    private final DestinationPointsFileProcessor destinationPointsFileProcessor;
    private final CommoditiesFileProcessor commoditiesFileProcessor;

    public Results process(File csv, String type) throws IOException {
        var processor = chooseProcessor(type);
        var dataFrame = readCsv(csv.getPath());
        var ok = processor.process(dataFrame);
        var error = dataFrame.length() - ok;
        return new Results(ok, error);
    }

    private DataFrame<Object> readCsv(String path) throws IOException {
        return DataFrame.readCsv(path);
    }

    private FileProcessor chooseProcessor(String type) {
        switch (type) {
            case PRODUCT: return productsFileProcessor;
            case CLIENT: return clientsFileProcessor;
            case DESTINATION_POINT: return destinationPointsFileProcessor;
            case ORDER: return ordersFileProcessor;
            case ORIGIN_POINT: return originPointsFileProcessor;
            default: return commoditiesFileProcessor;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Results {
        private final Integer ok;
        private final Integer error;
    }
}
