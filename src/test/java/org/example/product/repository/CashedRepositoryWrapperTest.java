package org.example.product.repository;

import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.example.product.csv_operations.utilities.CsvFileUtilImpl;
import org.example.product.csv_operations.mappers.CsvMapperImpl;
import org.example.product.csv_operations.modules.CsvFileReader;
import org.example.product.csv_operations.modules.CsvFileWriter;
import org.example.product.csv_operations.modules.FromCsvFileRemover;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;

class CashedRepositoryWrapperTest {

    private static final ProductDefinition EXAMPLE_PRODUCT_5 = new ProductDefinition(new Name("Banana5")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , LocalDate.now()
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a35"));

    private static final String EXAMPLE_PRODUCT_5_ID = "2f5cd2c6-a71c-4c57-a06a-bf4c422e9a35";

    @Test
    void testingIfRepositorySavesToCsv() {
        CashedRepositoryWrapper<ProductId, ProductDefinition> repositoryWrapper = new CashedRepositoryWrapper<>(
                new InFileRepository(
                        new File("src/main/resources/ProductsForRepository.csv"),
                        new CsvFileUtilImpl(
                                new CsvFileReader(),
                                new CsvFileWriter(),
                                new FromCsvFileRemover(
                                        new CsvFileReader(),
                                        new CsvFileWriter()
                                )
                        ),
                        new CsvMapperImpl()
                )
        );

        repositoryWrapper.save(EXAMPLE_PRODUCT_5);
        repositoryWrapper.delete(new ProductId(EXAMPLE_PRODUCT_5_ID));
    }
}