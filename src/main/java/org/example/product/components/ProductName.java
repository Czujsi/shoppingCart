package org.example.product.components;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public final class ProductName {
    private final String value;

    public ProductName(String productName) {

        this.value = productName.toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
