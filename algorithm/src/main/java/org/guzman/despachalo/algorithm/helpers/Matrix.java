package org.guzman.despachalo.algorithm.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class Matrix<T> {
    @Getter
    private Integer size;
    private ArrayList<ArrayList<T>> matrix = new ArrayList<>();

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

    public void addRow(ArrayList<T> row) {
        this.size = row.size();
        matrix.add(row);
    }
}
