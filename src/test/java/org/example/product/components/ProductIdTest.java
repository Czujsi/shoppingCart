package org.example.product.components;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class ProductIdTest {
    @Test
    void testingIdLength() {
        ProductId id = ProductId.createId();

        Assertions.assertThat(36).isEqualTo(id.toString().length());
    }
}