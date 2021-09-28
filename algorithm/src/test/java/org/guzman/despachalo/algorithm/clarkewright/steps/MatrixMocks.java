package org.guzman.despachalo.algorithm.clarkewright.steps;

import org.guzman.despachalo.algorithm.structures.Matrix;

import java.util.Collections;

public class MatrixMocks {
    public static Matrix<Double> zeroMatrix(Integer size) {
        var matrix = new Matrix<Double>();
        var row = Collections.nCopies(size, 0d);
        for (int i = 0; i < size; i++) {
            matrix.addRow(row);
        }

        return matrix;
    }

    public static Matrix<Double> incrementalMatrix(Integer size) {
        var matrix = new Matrix<Double>(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.set(i, j, i * j * 1d);
            }
        }

        return matrix;
    }
}
