package com.tiler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(fileReader)
                    .withMappingStrategy(strategy)
                    .build();
            List<Employee> staff = csv.parse();
            return staff;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String listToJson(List<T> list) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<T>>() {}.getType();
        String json = gson.toJson(list, listType);
        return json;
    }

    public static void writeString(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        String[] employee1 = {"1", "John", "Smith", "USA", "25"};
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeNext(employee1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] employee2 = {"2", "David", "Feezor", "USA", "40"};
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))) {
            writer.writeNext(employee2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        String fileJson = "json.json";
        writeString(fileJson, json);
    }
}
