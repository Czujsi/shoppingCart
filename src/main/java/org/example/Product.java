package org.example;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.currency_exchange.Currency;
import org.example.currency_exchange.Money;

import java.math.BigDecimal;

@EqualsAndHashCode
@ToString
public class Product {
    @EqualsAndHashCode.Exclude
    private Price price;

    @Getter
    private final ProductName productName;

    public Product(ProductName productName, Money price) {
        this(productName, new Price(price));
    }

    public Product(ProductName productName, Price price) {
        if (productName == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.productName = productName;
        this.price = price;
    }

    public static Product of(String productName, Money price){
        return new Product(new ProductName(productName), new Price(price));
    }

    public Money getPrice() {
        return this.price.productPrice;
    }



//    @Override
//    //a.equals (b)
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
//        if (this.getClass() != o.getClass()) return false;
//        Product other = (Product) o;
//        return Objects.equals(this.productName.toLowerCase(), other.productName.toLowerCase());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productName);
//    }


}
