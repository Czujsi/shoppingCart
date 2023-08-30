package org.example;

import lombok.ToString;

import java.util.Objects;

@ToString
public class Product {
    private final double productPrice;

    public Product(String productName, double productPrice) {
        this.productPrice = productPrice;
        if (productName == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }
        this.productName = productName;
    }

    @Override
    //a.equals (b)
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Product other = (Product) o;
        return Objects.equals(this.productName.toLowerCase(), other.productName.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    private final String productName;

    public Object getProductName() {
        return productName;
    }
}
