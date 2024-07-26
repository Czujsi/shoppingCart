package org.example.product.csv_operations.modules;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@Component
public class CsvFileCreator {

    public static void create(String fileName) {
        String resourcePath = "src/main/resources/";

        String filePath = resourcePath + fileName;

        File file = new File(filePath);

        CSVFormat csvFormat = CSVFormat.DEFAULT;

        try (FileWriter out = new FileWriter(file);
             CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
            System.out.println("File have been created at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
