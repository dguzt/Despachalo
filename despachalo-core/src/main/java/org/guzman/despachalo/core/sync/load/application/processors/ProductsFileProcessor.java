package org.guzman.despachalo.core.sync.load.application.processors;

import joinery.DataFrame;
import lombok.RequiredArgsConstructor;
import org.guzman.despachalo.core.sync.load.application.dataframe.EmptyFilter;
import org.guzman.despachalo.core.sync.load.application.dataframe.MapperDataFrame;
import org.guzman.despachalo.core.sync.load.application.dataframe.UniqueFilter;
import org.guzman.despachalo.core.sync.load.application.port.out.RegisterProductsPort;
import org.guzman.despachalo.core.sync.load.domain.ProductToRegister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import static org.guzman.despachalo.core.sync.load.application.identifier.ProductColumns.*;

@Service
@RequiredArgsConstructor
public class ProductsFileProcessor implements FileProcessor {
    private final EmptyFilter emptyFilter;
    private final UniqueFilter uniqueFilter;
    private final MapperDataFrame mapperDataFrame;
    private final RegisterProductsPort registerProductsPort;

    @Override
    public Integer process(DataFrame<Object> dataFrame) {
        var codeClean = dataFrame.select(emptyFilter.filterNoEmptyStrings(PRODUCT_CODE));
        var descClean = codeClean.select(emptyFilter.filterNoEmptyStrings(DESCRIPTION));
        var weightClean = descClean.select(emptyFilter.filterNoEmptyNumbers(WEIGHT));
        var volumeClean = weightClean.select(emptyFilter.filterNoEmptyNumbers(VOLUME));

        var uniqueCodes =  uniqueFilter.filterUnique(volumeClean, PRODUCT_CODE);
        var products = mapperDataFrame.mapToObj(uniqueCodes, mapToProduct());
        registerProductsPort.registerProducts(products);
        return products.size();
    }

    private Function<List<Object>, ProductToRegister> mapToProduct() {
        var now = LocalDateTime.now();
        return (row) -> ProductToRegister.builder()
                    .code(       (String) row.get(PRODUCT_CODE))
                    .description((String) row.get(DESCRIPTION))
                    .volume(     (Double)  row.get(VOLUME))
                    .weight(     (Double)  row.get(WEIGHT))
                    .createdAt(now)
                    .updatedAt(now)
                    .build();
    }
}
