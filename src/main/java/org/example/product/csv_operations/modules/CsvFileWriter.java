package org.example.product.csv_operations.modules;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;

@EqualsAndHashCode
@ToString
@Component
public class CsvFileWriter {
    public void writeToFile(File file, String productsAsText) {

        CSVFormat csvFormat = CSVFormat
                .DEFAULT
                .builder()
                .setSkipHeaderRecord(true)
                .setHeader(Headers.Name.name(), Headers.Price.name(), Headers.CreationDate.name(), Headers.ProductId.name())
                .build();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {

            printer.printRecord(productsAsText);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(File file, Collection<String> products) {

        CSVFormat csvFormat = CSVFormat
                .DEFAULT
                .builder()
                .setSkipHeaderRecord(true)
                .setHeader(Headers.Name.name(), Headers.Price.name(), Headers.CreationDate.name(), Headers.ProductId.name())
                .build();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
            for (String product : products) {
                printer.printRecord(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
