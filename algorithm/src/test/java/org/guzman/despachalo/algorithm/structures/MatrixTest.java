package org.guzman.despachalo.algorithm.structures;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    void givenEmptyMatrix_whenAddFirstRow_shouldSetSize() {
        var matrix = new Matrix<Double>();
        var row = List.of(1.0, 2.0, 3.0);
        matrix.addRow(row);

        assertEquals(row.size(), matrix.getSize());
    }

    @Test
    void givenMatrixWithOneRow_whenAddSecondRowWithDiffSize_shouldThrowIllegalArgException() {
        var matrix = new Matrix<Double>();
        var firstRow = List.of(1.0, 2.0, 3.0);
        var secondRow = List.of(1.0, 2.0, 3.0, 4.0);

        matrix.addRow(firstRow);

        assertThrows(IllegalArgumentException.class, () -> matrix.addRow(secondRow));
    }

    @Test
    void givenSizedMatrix_whenAddValuesInCells_shouldNoThrowAnyException() {
        var matrix = new Matrix<Integer>(5);
        matrix.set(0, 0, 10);

        assertEquals(10, matrix.get(0, 0));
        assertNull(matrix.get(1, 1));
    }
}
