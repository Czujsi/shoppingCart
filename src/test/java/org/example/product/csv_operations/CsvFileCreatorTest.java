package org.example.product.csv_operations;

import org.assertj.core.api.Assertions;
import org.example.product.csv_operations.modules.CsvFileCreator;
import org.junit.jupiter.api.Test;

import java.io.File;

class CsvFileCreatorTest {
    @Test
    void creatingAFileInWantedPlace() {
        CsvFileCreator.create("test6.csv");

        File file = new File("src/main/resources/test6.csv");

        Assertions.assertThat(file.exists()).isTrue();
        file.deleteOnExit();
    }
}