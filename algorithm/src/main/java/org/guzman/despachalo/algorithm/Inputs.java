package org.guzman.despachalo.algorithm;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.guzman.despachalo.algorithm.helpers.Matrix;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Inputs {
    private static final String PAIRWISE_CSV = "/home/jhair/dev/projects/despachalo/core/algorithm/src/main/resources/excel/pairwise.csv";
    private static final String DEMAND_CSV = "/home/jhair/dev/projects/despachalo/core/algorithm/src/main/resources/excel/demand.csv";

    public static ArrayList<Double> demand() {
        var demand = new ArrayList<Double>();

        try (var csvReader = new CSVReader(new FileReader(DEMAND_CSV))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                var d = Double.parseDouble(values[0]);
                demand.add(d);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Cannot read demands csv");
        }

        return demand;
    }

    public static Matrix<Double> costs() {
        var matrix = new Matrix<Double>();

        try (var csvReader = new CSVReader(new FileReader(PAIRWISE_CSV))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                var row = new ArrayList<Double>();

                for (String value : values) {
                    row.add(Double.parseDouble(value));
                }

                matrix.addRow(row);
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Cannot read pair costs csv");
        }

        return matrix;
    }
}
