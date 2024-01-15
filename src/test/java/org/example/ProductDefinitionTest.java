package org.example;

import org.example.currency_exchange_money.Money;
import org.example.product.components.DateForProduct;
import org.example.product.components.Price;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.example.currency_exchange_money.Currency.*;

class ProductDefinitionTest {
    public static final String PRODUCT_NAME = "Butter";
    DateForProduct dateForProduct = new DateForProduct(LocalDate.now());
    @Test
    void qualityTest() {
        ProductDefinition a = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)), dateForProduct);
        ProductDefinition b = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)), dateForProduct);
        assertThat(a).isEqualTo(b);
    }

    //We're testing for that
    @SuppressWarnings("DataFlowIssue")
    @Test
    void checkingIf_whenAddedNewProductWithNullProductName_thenMethodThrowsException() {
        assertThatThrownBy(() -> new ProductDefinition(null, new Price(Money.of(2.50, PLN)), dateForProduct))
                .hasMessage("You cannot add or remove product with null name");
    }

    @Test
    void checkingIfEqualMethodWillFindSameNamesOfItemsButWithDifferentSizeLetters() {
        ProductDefinition a = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)), dateForProduct);
        ProductDefinition b = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)), dateForProduct);
        assertThat(a).isEqualTo(b);
    }
    
    @Nested
    public class History{
        @Test
        void name() {
        }
    }
}
