package org.example.product.csv_operations;

import org.assertj.core.api.Assertions;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.example.product.csv_operations.modules.CsvFileReader;
import org.example.product.csv_operations.modules.CsvFileWriter;
import org.example.product.csv_operations.modules.FromCsvFileRemover;
import org.example.product.csv_operations.utilities.CsvFileUtilImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

class FileManagerImplTest {
    // region EXAMPLE PRODUCTS
    private static final ProductDefinition EXAMPLE_PRODUCT_1 = new ProductDefinition(new Name("Apple")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , LocalDate.now()
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a36"));

    private static final ProductDefinition EXAMPLE_PRODUCT_2 = new ProductDefinition(new Name("Meat")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , LocalDate.now()
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a37"));
    private static final ProductDefinition EXAMPLE_PRODUCT_3 = new ProductDefinition(new Name("Keyboard")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , LocalDate.now()
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a38"));
    private static final ProductDefinition EXAMPLE_PRODUCT_4 = new ProductDefinition(new Name("Bicycle")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , LocalDate.now()
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a39"));

    private static final ProductDefinition EXAMPLE_PRODUCT_5 = new ProductDefinition(new Name("Car")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , LocalDate.now()
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a40"));
    // endregion

    private final File file = new File("src/main/resources/test3.csv");
    private final File file1 = new File("src/main/resources/test10.csv");

    private static final List<String> EXPECTED_FILESTREAM = List.of(
            "banana3;2.65;2024-07-04;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a33",
            "banana3;2.65;2024-07-04;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a33"
    );


    @Test
    void creatingAFile() {
        CsvFileReader csvFileReader = new CsvFileReader();
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        CsvFileUtilImpl csvFileUtil = new CsvFileUtilImpl(csvFileReader, csvFileWriter, MockFromFileRemover.INSTANCE);


        csvFileUtil.writeCollectionToFile(file, EXPECTED_FILESTREAM);
        List<String> actual = csvFileUtil.readFile(file);
        String expected = EXPECTED_FILESTREAM.get(1);

        Assertions.assertThat(expected).isEqualTo(actual.getFirst());
    }

    @ParameterizedTest
    @MethodSource("expectedListProvider")
    void checkingIfFileSavesAsExpected(List<String> expected) {
        CsvFileReader csvFileReader = new CsvFileReader();
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        CsvFileUtilImpl csvFileManager = new CsvFileUtilImpl(csvFileReader, csvFileWriter, MockFromFileRemover.INSTANCE);

        List<String> actual = csvFileManager.readFile(file1);

        Assertions.assertThat(actual)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expected);
    }

    private static Stream<Arguments> expectedListProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        "banana3;2.65;2024-07-04;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a33",
                        "banana2;2.65;2024-07-04;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a32",
                        "banana1;2.65;2024-07-04;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a31",
                        "banana5;2.65;2024-07-04;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a35",
                        "banana4;2.65;2024-07-04;2f5cd2c6-a71c-4c57-a06a-bf4c422e9a34"
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("productsProvider")
    void writingToAFile(Set<ProductDefinition> products) {
        CsvFileReader csvFileReader = new CsvFileReader();
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        CsvFileUtilImpl csvFileManager = new CsvFileUtilImpl(csvFileReader, csvFileWriter, MockFromFileRemover.INSTANCE);

        csvFileManager.writeToFile(file, products.toString());

        List<String> actual = csvFileManager.readFile(file1);

        Assertions.assertThat(actual.getFirst()).isEqualTo(actual.getFirst());
    }

    private static Stream<Set<ProductDefinition>> productsProvider() {
        return Stream.of(Set.of(EXAMPLE_PRODUCT_1, EXAMPLE_PRODUCT_2, EXAMPLE_PRODUCT_3, EXAMPLE_PRODUCT_4, EXAMPLE_PRODUCT_5));
    }

    //region Mocks
    static class MockFromFileRemover extends FromCsvFileRemover {
        private static final CsvFileReader csvFileReader = new CsvFileReader();
        private static final CsvFileWriter csvFileWriter = new CsvFileWriter();

        public final static FromCsvFileRemover INSTANCE = new FileManagerImplTest.MockFromFileRemover(csvFileReader, csvFileWriter);

        public MockFromFileRemover(CsvFileReader csvFileReader, CsvFileWriter csvFileWriter) {
            super(csvFileReader, csvFileWriter);
        }

        @Override
        public void removeRecord(File file, String productId) {

        }
    }
    //endregion
}




