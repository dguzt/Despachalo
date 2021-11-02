package org.guzman.despachalo.core.sync.load.application.dataframe;

import joinery.DataFrame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniqueFilter {
    private final CopyDataFrame copyDataFrame;

    public DataFrame<Object> filterUnique(DataFrame<Object> dataFrame, Integer uniqueColumn) {
        return dataFrame.unique(uniqueColumn);
    }

    public DataFrame<Object> filterNoUnique(DataFrame<Object> dataFrame, Integer uniqueColumn) {
        var unique = filterUnique(dataFrame, uniqueColumn);
        if (unique.length() == dataFrame.length()) {
            return copyDataFrame.genEmpty(dataFrame.columns());
        }

        var uniqueValues = unique.col(uniqueColumn);
        return dataFrame.select((r) -> {
            var value = r.get(uniqueColumn);

            if (uniqueValues.contains(value)) {
                uniqueValues.remove(value);
                return false;
            }

            return true;
        });
    }
}
