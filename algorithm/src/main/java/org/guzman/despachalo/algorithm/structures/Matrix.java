package org.guzman.despachalo.algorithm.structures;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Matrix<T> {
    @Getter
    private Integer size;
    private List<List<T>> matrix = new ArrayList<>();

    public Matrix(Integer size) {
        this.size = size;
        this.matrix = new ArrayList<>(size);

        for (int r = 0; r < size; r++) {
            var row = new ArrayList<T>(size);
            for (int c = 0; c < size; c++) {
                row.add(null);
            }
            this.matrix.add(row);
        }
    }

    public T get(Integer row, Integer col) {
        return matrix.get(row).get(col);
    }

    public void set(Integer row, Integer col, T val) {
        matrix.get(row).set(col, val);
    }

    public void addRow(List<T> row) {
        if (this.size == null) {
            this.size = row.size();
        }

        if (this.size != row.size()) {
            throw new IllegalArgumentException(String.format("Rows must be the same size as the other rows in matrix. Matrix size: %d, Row size given: %d",
                    this.size,
                    row.size()));
        }

        matrix.add(row);
    }
}
