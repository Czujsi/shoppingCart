package org.example.product.repository;

import org.assertj.core.api.ThrowableAssert;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.CreationDate;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ProductRepositoryFileImplTest {
    static ProductDefinition EXAMPLE_PRODUCT_1 = new ProductDefinition(new Name("Banana1")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , new CreationDate(LocalDate.now())
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a31"));

    static ProductDefinition EXAMPLE_PRODUCT_2 = new ProductDefinition(new Name("Banana2")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , new CreationDate(LocalDate.now())
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a32"));
    static ProductDefinition EXAMPLE_PRODUCT_3 = new ProductDefinition(new Name("Banana3")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , new CreationDate(LocalDate.now())
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a33"));
    static ProductDefinition EXAMPLE_PRODUCT_4 = new ProductDefinition(new Name("Banana4")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , new CreationDate(LocalDate.now())
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a34"));

    static ProductDefinition EXAMPLE_PRODUCT_5 = new ProductDefinition(new Name("Banana5")
            , new Price(Money.of(BigDecimal.valueOf(2.65), Currency.PLN))
            , new CreationDate(LocalDate.now())
            , new ProductId("2f5cd2c6-a71c-4c57-a06a-bf4c422e9a35"));

    @Test
    void name() {
        ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl();

        System.out.println(productRepositoryFile.getAll().stream().map(ProductDefinition::toString).collect(Collectors.joining("\n")));
    }

    // checking if cache memory is faster than parsing from csv file always for every need, and how fast it is
    @Test
    void checkingIfInMemoryListIsMoreOptimal() {
        ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl();

        System.out.println(productRepositoryFile.getAll1().stream().map(ProductDefinition::toString).collect(Collectors.joining("\n")));
    }

    @Test
    void testingIfSavingMethodWorksAsIntended() {
        ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl();

        productRepositoryFile.save(EXAMPLE_PRODUCT_1);
        productRepositoryFile.save(EXAMPLE_PRODUCT_2);
        productRepositoryFile.save(EXAMPLE_PRODUCT_3);
        productRepositoryFile.save(EXAMPLE_PRODUCT_4);

        assertThat(productRepositoryFile.exists(EXAMPLE_PRODUCT_1.getProductId())).isTrue();
        assertThat(productRepositoryFile.exists(EXAMPLE_PRODUCT_2.getProductId())).isTrue();
        assertThat(productRepositoryFile.exists(EXAMPLE_PRODUCT_3.getProductId())).isTrue();
        assertThat(productRepositoryFile.exists(EXAMPLE_PRODUCT_4.getProductId())).isTrue();
    }

    @Test
    void testingIfSavingMethodWorksAsIntended2() {
        ProductRepositoryFileImpl productRepositoryFile = new ProductRepositoryFileImpl();
        productRepositoryFile.save(EXAMPLE_PRODUCT_5);

        ThrowableAssert.ThrowingCallable addItem = () -> productRepositoryFile.save(EXAMPLE_PRODUCT_5);

        assertThatThrownBy(addItem).hasMessage("Product already exists");
    }
}