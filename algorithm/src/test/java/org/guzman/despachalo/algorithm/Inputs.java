package org.guzman.despachalo.algorithm;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.guzman.despachalo.algorithm.structures.Matrix;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Inputs {
    private static final String PAIRWISE_CSV = "excel/pairwise.csv";
    private static final String DEMAND_CSV = "excel/demand.csv";

    public static ArrayList<Double> demand() {
        var demand = new ArrayList<Double>();
        var url = Inputs.class.getClassLoader().getResource(DEMAND_CSV);

        try (var csvReader = new CSVReader(new FileReader(url.getFile()))) {
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
        var url = Inputs.class.getClassLoader().getResource(PAIRWISE_CSV);

        try (var csvReader = new CSVReader(new FileReader(url.getFile()))) {
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
