package org.guzman.despachalo.core.sync.load.application.dataframe;

import joinery.DataFrame;
import org.springframework.stereotype.Service;

@Service
public class EmptyFilter {
    public DataFrame.Predicate<Object> filterNoEmptyStrings(Integer columnIndex) {
        return (row) -> {
            try {
                var value = row.get(columnIndex).toString();
                return value != null && !value.isEmpty();
            } catch (RuntimeException e) {
                return false;
            }
        };
    }

    private DataFrame.Predicate<Object> filterEmptyStrings(Integer columnIndex) {
        return (row) -> {
            try {
                var value = (String) row.get(columnIndex);
                return value == null || value.isEmpty();
            } catch (RuntimeException e) {
                return true;
            }
        };
    }

    public DataFrame.Predicate<Object> filterNoEmptyNumbers(Integer columnIndex) {
        return (row) -> {
            try {
                var value = (Double) row.get(columnIndex);
                return value != null && !value.equals(0.0);
            } catch (RuntimeException e) {
                return false;
            }
        };
    }

    public DataFrame.Predicate<Object> filterEmptyNumbers(Integer columnIndex) {
        return (row) -> {
            try {
                var value = (Double) row.get(columnIndex);
                return value == null || value.equals(0.0);
            } catch (RuntimeException e) {
                return true;
            }
        };
    }
}
