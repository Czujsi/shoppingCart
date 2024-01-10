package org.example.product;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.currency_exchange_money.Money;
import org.example.product.components.DateForProduct;
import org.example.product.components.Price;
import org.example.product.components.ProductName;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
public class ProductDefinition {
    @EqualsAndHashCode.Exclude
    private final Price price;

    @Getter
    private final ProductName productName;
    @Getter
    private final DateForProduct localDate;

    public ProductDefinition(ProductName productName, Price price, DateForProduct localDateForProduct) {
        if (productName == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.productName = productName;
        this.price = price;
        this.localDate = localDateForProduct;
    }

    public static ProductDefinition of(String productName, Money price) {
        return new ProductDefinition(new ProductName(productName), new Price(price), new DateForProduct(LocalDate.now()));
    }

    public Money getPrice() {
        return this.price.productPrice;
    }
}
