package org.guzman.despachalo.algorithm;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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
            e.printStackTrace();
        }

        return demand;
    }

    public static double[][] costs() {
        var records = new ArrayList<double[]>();
        try (var csvReader = new CSVReader(new FileReader(PAIRWISE_CSV))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                var row = new double[values.length];

                for (int i = 0; i < values.length; i++) {
                    row[i] = Double.parseDouble(values[i]);
                }

                records.add(row);
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        var res = new double[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            res[i] = records.get(i);
        }

        return res;
    }
}
