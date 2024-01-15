package org.example.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ProductHistoryTest {
    @Test
    void changingProductNameTestMethod(){
        ProductHistory productHistory = new ProductHistory();
        productHistory.append(new Change<>(Change.ChangeType.NAME, "d"));

        Assertions.assertThat(
                productHistory.versionControl(Change.ChangeType.NAME))
                .contains(List.of(new Change<>(Change.ChangeType.NAME, "d")));
    }
    @Test
    void changingProductPriceTestMethod(){
        ProductHistory productHistory = new ProductHistory();
        productHistory.append(new Change<>(Change.ChangeType.PRICE, "d"));

        Assertions.assertThat(
                productHistory.versionControl(Change.ChangeType.PRICE))
                .contains(List.of(new Change<>(Change.ChangeType.PRICE, "d")));
    }
    @Test
    void changingProductPriceIsNotEmptyTestMethod(){
        ProductHistory productHistory = new ProductHistory();
        productHistory.append(new Change<>(Change.ChangeType.PRICE, "d"));

        Assertions.assertThat(productHistory.versionControl(Change.ChangeType.PRICE)).isNotEmpty();
    }
    @Test
    void changingProductNameIsNotEmptyTestMethod(){
        ProductHistory productHistory = new ProductHistory();
        productHistory.append(new Change<>(Change.ChangeType.NAME, "d"));

        Assertions.assertThat(productHistory.versionControl(Change.ChangeType.NAME)).isNotEmpty();
    }
}