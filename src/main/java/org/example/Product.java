package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Product {
    @Getter
    private final Price price;

    @Getter
    private String productName;

    public Product(ProductName productName, Price price1) {
        if (this.productName == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.productName = this.productName;
        this.price = price1;
    }
}
