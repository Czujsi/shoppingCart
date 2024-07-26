package org.example.product.csv_operations.modules;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@Component
public class FromCsvFileRemover {
    private final CsvFileReader csvFileReader;
    private final CsvFileWriter csvFileWriter;
    private final HashSet<String> recordsAfterDeletingSpecifiedProduct = new HashSet<>();

    public void removeRecord(File file, String productId) {
        checkingForEmptyArguments(productId);
        productId = changingForLowerCaseArgument(productId);
        searchAndRemoveRow(file, productId);
        updateFileContent(file);
    }


    private static void checkingForEmptyArguments(String productId) {
        if (productId.isEmpty()) {
            throw new IllegalArgumentException("You need to type product Id that you want to delete");
        }
    }

    private static String changingForLowerCaseArgument(String productId) {
        return productId.toLowerCase();
    }

    private void searchAndRemoveRow(File file, String productId) {
        recordsAfterDeletingSpecifiedProduct.clear();
        HashSet<String> recordsBeforeDeletingSpecifiedProduct = new HashSet<>(csvFileReader.read(file));
        for (String row : recordsBeforeDeletingSpecifiedProduct) {
            String[] rows = row.split(";");
            if (!rows[3].contains(productId)) {
                recordsAfterDeletingSpecifiedProduct.add(row);
            }
        }
    }

    private void updateFileContent(File file) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(file.getPath()))) {
            bufferedWriter.write("");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        csvFileWriter.writeToFile(file, recordsAfterDeletingSpecifiedProduct);
    }
}
