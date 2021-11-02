package org.guzman.despachalo.core.sync.load.application.dataframe;

import joinery.DataFrame;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CopyDataFrame {
    public DataFrame<Object> genEmpty(Collection<?> columns) {
        return new DataFrame<>(columns);
    }
}
