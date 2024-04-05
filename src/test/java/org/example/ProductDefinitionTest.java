package org.example;

import org.example.currency_exchange_money.Money;
import org.example.product.components.Price;
import org.example.product.ProductDefinition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.example.currency_exchange_money.Currency.*;

class ProductDefinitionTest {

    //We're testing for that
    @SuppressWarnings("DataFlowIssue")
    @Test
    void checkingIf_whenAddedNewProductWithNullProductName_thenMethodThrowsException() {
        assertThatThrownBy(() -> new ProductDefinition(null, new Price(Money.of(BigDecimal.valueOf(2.50), PLN)), LocalDate.now()))
                .hasMessage("You cannot add or remove product with null name");
    }

    @Nested
    public class History {
        @Test
        void name() {
        }
    }
}
