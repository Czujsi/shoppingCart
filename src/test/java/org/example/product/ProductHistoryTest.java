package org.example.product;

import org.example.product.history.Change;
import org.example.product.history.ProductHistory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductHistoryTest {

    public static final Change<String> FIRST_EXAMPLE_NAME_CHANGE = new Change<>(Change.ChangeType.NAME, "d");
    public static final Change<String> SECOUND_EXAMPLE_NAME_CHANGE = new Change<>(Change.ChangeType.NAME, "b");
    public static final Change<String> THIRD_EXAMPLE_NAME_CHANGE = new Change<>(Change.ChangeType.NAME, "c");
    public static final Change<String> EXAMPLE_PRICE_CHANGE = new Change<>(Change.ChangeType.PRICE, "d");

    @Test
    void changingProductNameTestMethod() {
        // Given
        ProductHistory productHistory = new ProductHistory();

        // When
        productHistory.append(FIRST_EXAMPLE_NAME_CHANGE);

        // Then
        assertThat(productHistory.versionControl(Change.ChangeType.NAME))
                .isNotEmpty()
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("timestamp")
                .contains(FIRST_EXAMPLE_NAME_CHANGE);
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

    @Test
    void searchingChangedProductByNameAndChangeType() {
        ProductHistory productHistory = new ProductHistory();

        productHistory.append(FIRST_EXAMPLE_NAME_CHANGE);
        productHistory.append(SECOUND_EXAMPLE_NAME_CHANGE);
        productHistory.append(THIRD_EXAMPLE_NAME_CHANGE);

        assertThat(productHistory.getHistoryByNameAndChangeType("d", Change.ChangeType.NAME)
                .contains(FIRST_EXAMPLE_NAME_CHANGE))
                .isTrue();
    }
}