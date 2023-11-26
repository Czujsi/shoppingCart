package org.example;

import org.example.currency_exchange_money.Money;
import org.example.product.components.Price;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.example.currency_exchange_money.Currency.*;

class ProductDefinitionTest {
    //public static final DiscountDefinition FLAT_10_PERCENT_DISCOUNT_DEFINITION = new FlatPercentDiscount("abc", 10.0);
    public static final String PRODUCT_NAME = "Butter";


    @Test
    void qualityTest() {
        ProductDefinition a = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        ProductDefinition b = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        assertThat(a).isEqualTo(b);
    }

    //We're testing for that
    @SuppressWarnings("DataFlowIssue")
    @Test
    void checkingIf_whenAddedNewProductWithNullProductName_thenMethodThrowsException() {

        assertThatThrownBy(() -> new ProductDefinition(null, new Price(Money.of(2.50, PLN)))).hasMessage("You cannot add or remove productDefinition with null name");

    }

    @Test
    void checkingIfEqualMethodWillFindSameNamesOfItemsButWithDifferentSizeLetters() {
        ProductDefinition a = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        ProductDefinition b = new ProductDefinition(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        assertThat(a).isEqualTo(b);
    }

   // @Test
   // void simpleTest() {
   //     // given
   //     ProductDefinition productDefinition = new ProductDefinition(new ProductName("Butter"), Money.of(10.0, PLN));
//
   //     // when
   //     productDefinition.applyDiscount(FLAT_10_PERCENT_DISCOUNT_DEFINITION);
//
   //     // then
   //     assertThat(productDefinition.getPrice())
   //             .isNotNull()
   //             .isEqualTo(Money.of(9.0, PLN));
   // }
}
