package org.example.product.csv_operations.utilities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.fileUtilities.FileUtil;
import org.example.product.components.ProductId;
import org.example.product.csv_operations.modules.CsvFileCreator;
import org.example.product.csv_operations.modules.CsvFileReader;
import org.example.product.csv_operations.modules.CsvFileWriter;
import org.example.product.csv_operations.modules.FromCsvFileRemover;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Component
public class CsvFileUtilImpl implements FileUtil {

    private final CsvFileReader csvFileReader;
    private final CsvFileWriter csvFileWriter;
    private final FromCsvFileRemover fromFileRemover;


    @Override
    public boolean checkIfFileExist(File file) {
        if (file.exists()) {
            return true;
        } else {
            throw new RuntimeException("Path or file does not exist");
        }
    }

    @Override
    public void createFile(String fileName) {
        CsvFileCreator.create(fileName);
    }

    @Override
    public void deleteFile(String filePath) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteFromFile(File file, ProductId productId) {
        fromFileRemover.removeRecord(file, productId.getValue().toString());
    }

    @Override
    public void writeToFile(File file, String productsAsText) {
        csvFileWriter.writeToFile(file, productsAsText);
    }

    @Override
    public void writeCollectionToFile(File file, Collection<String> productDefinitions) {
        csvFileWriter.writeToFile(file, productDefinitions);
    }

    @Override
    public List<String> readFile(File file) {
        return csvFileReader.read(file);
    }
}
