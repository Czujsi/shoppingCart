package org.example.product;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductHistoryTest {

    public static final Change<String> EXAMPLE_NAME_CHANGE = new Change<>(Change.ChangeType.NAME, "d");
    public static final Change<String> EXAMPLE_PRICE_CHANGE = new Change<>(Change.ChangeType.PRICE, "d");

    @Test
    void changingProductNameTestMethod() {
        // Given
        ProductHistory productHistory = new ProductHistory();

        // When
        productHistory.append(EXAMPLE_NAME_CHANGE);

        // Then
        assertThat(productHistory.versionControl(Change.ChangeType.NAME))
                .isNotEmpty()
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("timestamp")
                .contains(EXAMPLE_NAME_CHANGE);
    }

    @Test
    void changingProductPriceTestMethod() {
        ProductHistory productHistory = new ProductHistory();

        productHistory.append(EXAMPLE_PRICE_CHANGE);

        assertThat(productHistory.versionControl(Change.ChangeType.PRICE))
                .isNotEmpty()
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("timestamp")
                .contains(EXAMPLE_PRICE_CHANGE);
    }
}