package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void qualityTest() {
        Product a = new Product("Butter", 12.50);
        Product b = new Product("Butter", 12.50);
        Assertions.assertThat(a).isEqualTo(b);
    }

    @Test
    void checkingIf_whenAddedNewProductWithNullProductName_thenMethodThrowsException() {

        Assertions.assertThatThrownBy(() -> new Product(null, 12.50)).hasMessage("You cannot add or remove product with null name");

    }

    @Test
    void checkingIfEqualMethodWillFindSameNamesOfItemsButWithDifferentSizeLetters() {
        Product a = new Product("Butter", 12.50);
        Product b = new Product("bUtTer", 12.50);
        Assertions.assertThat(a).isEqualTo(b);
    }
}