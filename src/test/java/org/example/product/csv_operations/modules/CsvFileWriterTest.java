package org.example.product.csv_operations.modules;

import lombok.extern.slf4j.Slf4j;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.csv_operations.mappers.CsvMapperImpl;
import org.example.product.csv_operations.mappers.Mapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.currency_exchange_money.Currency.PLN;

@Slf4j
class CsvFileWriterTest {
    private static final File EXAMPLE_FILE = new File("src/main/resources/test5.csv");

    private static final Collection<ProductDefinition> exampleProducts = new ArrayList<>();

    private static final Collection<ProductDefinition> newExampleProducts = new ArrayList<>();

    private static final Collection<ProductDefinition> allExampleProducts = new ArrayList<>();

    private final Mapper<ProductDefinition, String> mapper = new CsvMapperImpl();

    @BeforeEach
    void setExampleProducts() {
        exampleProducts.add(new ProductDefinition(new Name("meat"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()));
        exampleProducts.add(new ProductDefinition(new Name("keyboard"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()));
        exampleProducts.add(new ProductDefinition(new Name("bicycle"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()));
        exampleProducts.add(new ProductDefinition(new Name("car"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()));
        exampleProducts.add(new ProductDefinition(new Name("apple"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()));
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        List<String> exampleProductsAsText = exampleProducts.stream().map(mapper::deserialize).toList();
        csvFileWriter.writeToFile(EXAMPLE_FILE, exampleProductsAsText);

        newExampleProducts.add(new ProductDefinition(new Name("BUTTER"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()));
        newExampleProducts.add(new ProductDefinition(new Name("BANANA"), new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()));

        allExampleProducts.addAll(exampleProducts);
        allExampleProducts.addAll(newExampleProducts);
//        meat;2.65;2024-07-06;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a37
//        keyboard;2.65;2024-07-06;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a38
//        bicycle;2.65;2024-07-06;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a39
//        car;2.65;2024-07-06;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a40
//        apple;2.65;2024-07-06;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a36
    }
    @AfterAll
    static void removeAfterTest() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(() -> {
            try {
                Thread.sleep(10000);
                Files.delete(EXAMPLE_FILE.toPath());
                log.info("File {} deleted", EXAMPLE_FILE.getPath());
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testingIfWriteMethodWriteWithoutDeletingExistingData() {
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        CsvFileReader csvFileReader = new CsvFileReader();
        FromCsvFileRemover fromCsvFileRemover = new FromCsvFileRemover(csvFileReader, csvFileWriter);
        List<String> newExampleProductsAsText = newExampleProducts.stream().map(mapper::deserialize).toList();
        csvFileWriter.writeToFile(EXAMPLE_FILE, newExampleProductsAsText);

        List<String> actualList = csvFileReader.read(EXAMPLE_FILE);
        List<String> expected = allExampleProducts.stream().map(mapper::deserialize).toList();

        assertThat(actualList).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);

        for (String product : expected){
            fromCsvFileRemover.removeRecord(EXAMPLE_FILE, product);
        }
    }
}