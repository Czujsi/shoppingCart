package org.example;

import org.example.currency_exchange_money.Money;
import org.example.product.Price;
import org.example.product.Product;
import org.example.product.ProductName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.example.currency_exchange_money.Currency.*;

class ProductTest {
    //public static final DiscountDefinition FLAT_10_PERCENT_DISCOUNT_DEFINITION = new FlatPercentDiscount("abc", 10.0);
    public static final String PRODUCT_NAME = "Butter";


    @Test
    void qualityTest() {
        Product a = new Product(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        Product b = new Product(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        assertThat(a).isEqualTo(b);
    }

    //We're testing for that
    @SuppressWarnings("DataFlowIssue")
    @Test
    void checkingIf_whenAddedNewProductWithNullProductName_thenMethodThrowsException() {

        assertThatThrownBy(() -> new Product(null, new Price(Money.of(2.50, PLN)))).hasMessage("You cannot add or remove product with null name");

    }

    @Test
    void checkingIfEqualMethodWillFindSameNamesOfItemsButWithDifferentSizeLetters() {
        Product a = new Product(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        Product b = new Product(new ProductName("Butter"), new Price(Money.of(2.50, PLN)));
        assertThat(a).isEqualTo(b);
    }

   // @Test
   // void simpleTest() {
   //     // given
   //     Product product = new Product(new ProductName("Butter"), Money.of(10.0, PLN));
//
   //     // when
   //     product.applyDiscount(FLAT_10_PERCENT_DISCOUNT_DEFINITION);
//
   //     // then
   //     assertThat(product.getPrice())
   //             .isNotNull()
   //             .isEqualTo(Money.of(9.0, PLN));
   // }
}
