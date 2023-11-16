package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public final class ProductName {
    private final String value;

    public ProductName(String productName) {

        this.value = productName.toLowerCase();
    }
}
