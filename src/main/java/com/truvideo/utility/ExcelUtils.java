package com.truvideo.utility;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.FilePayload;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelUtils extends JavaUtility{

    public static Object[][] readCSV(String filePath) throws CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            csvReader.readNext(); // Skip header
            return csvReader.readAll().stream().map(row -> new Object[] { row[0], row[1], row[2], row[3] })
                    .toArray(Object[][]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0];
        }

    }

    public void createAndUploadCsvFile_Technician(Page page) {
        String filePath = "./src/test/resources/CreateUserData" + "unique_data_technician.csv";
        createCsvFile_Technician(filePath);
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            page.setInputFiles("input[type='file']", new FilePayload(filePath, "text/csv", fileContent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCsvFile_Technician(String filePath) {
        String[] headers = { "Firstname", "Lastname", "Title" };
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String header : headers) {
                writer.append(header).append(",");
            }
            writer.append("\n");
            for (int i = 0; i < 3; i++) {
                String[] data = { generateUniqueString(), generateUniqueString(), "Technician" };
                for (String field : data) {
                    writer.append(field).append(",");
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
