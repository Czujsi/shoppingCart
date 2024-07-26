package org.example.product.csv_operations;

import org.assertj.core.api.Assertions;
import org.example.product.csv_operations.modules.CsvFileReader;
import org.example.product.csv_operations.modules.CsvFileWriter;
import org.example.product.csv_operations.modules.FromCsvFileRemover;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class FromFileRemoverTest {
    private static final File FILE = new File("src/main/resources/test2.csv");
    private static final String EXAMPLE_PRODUCT = "bicycle;2.5;2024-07-11;584f6e89-a46f-40a3-ae79-a7d5407945dc";
    private static final String EXAMPLE_PRODUCT_ID = "584f6e89-a46f-40a3-ae79-a7d5407945dc";
    private static final String EXAMPLE_PRODUCT_ID_UPPER_CASE = "584F6E89-A46F-40A3-AE79-A7D5407945DC";

    private static final CsvFileReader csvFileReader = new CsvFileReader();
    private final CsvFileWriter csvFileWriter = new CsvFileWriter();

    @Test
    void removingRecordFromCSVFile() {
        FromCsvFileRemover fromFileRemover = new FromCsvFileRemover(csvFileReader, csvFileWriter);

        fromFileRemover.removeRecord(FILE, EXAMPLE_PRODUCT_ID);
        List<String> exampleProductList = new ArrayList<>(csvFileReader.read(FILE));

        Assertions.assertThat(exampleProductList).doesNotContain(EXAMPLE_PRODUCT);

        //returning file to original state
        csvFileWriter.writeToFile(FILE, EXAMPLE_PRODUCT);
    }

    @Test
    void addingSafeguardForUpperCaseArguments() {
        FromCsvFileRemover fromFileRemover = new FromCsvFileRemover(csvFileReader, csvFileWriter);

        fromFileRemover.removeRecord(FILE, EXAMPLE_PRODUCT_ID_UPPER_CASE);
        List<String> exampleProductList = new ArrayList<>(csvFileReader.read(FILE));

        Assertions.assertThat(exampleProductList).doesNotContain(EXAMPLE_PRODUCT);

        //returning file to original state
        csvFileWriter.writeToFile(FILE, EXAMPLE_PRODUCT);
    }
}