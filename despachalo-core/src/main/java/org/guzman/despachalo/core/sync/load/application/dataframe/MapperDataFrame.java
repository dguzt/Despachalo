package org.guzman.despachalo.core.sync.load.application.dataframe;

import joinery.DataFrame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MapperDataFrame {
    public <V> List<V> mapToObj(DataFrame<Object> dataFrame, Function<List<Object>, V> mapper) {
        return IntStream.range(0, dataFrame.length())
                .mapToObj(index -> {
                    var row = dataFrame.row(index);
                    return mapper.apply(row);
                })
                .collect(Collectors.toList());
    }
}
