package org.example.product.components;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public final class Name {
    private final String value;

    public Name(String productName) {
        this.value = productName.toLowerCase();
    }
}
