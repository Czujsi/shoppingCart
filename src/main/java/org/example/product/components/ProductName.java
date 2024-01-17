package org.example.product.components;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public final class ProductName {
    private final String value;

    public ProductName(String productName) {
        this.value = productName.toLowerCase();
    }
}
