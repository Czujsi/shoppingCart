package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void qualityTest() {
        Product a = new Product("Butter");
        Product b = new Product("Butter");
        Assertions.assertThat(a).isEqualTo(b);
    }

    @Test
    void checkingIf_whenAddedNewProductWithNullProductName_thenMethodThrowsException() {

        Assertions.assertThatThrownBy(() -> new Product(null)).hasMessage("You cannot add null product name");

    }

    @Test
    void checkingIfEqualMethodWillFindSameNamesOfItemsButWithDifferentSizeLetters() {
        Product a = new Product("Butter");
        Product b = new Product("bUtTer");
        Assertions.assertThat(a).isEqualTo(b);
    }
}