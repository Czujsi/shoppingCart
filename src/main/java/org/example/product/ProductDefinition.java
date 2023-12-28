package org.example.product;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.currency_exchange_money.Money;
import org.example.product.components.Price;
import org.example.product.components.ProductName;

@EqualsAndHashCode
@ToString
public class ProductDefinition {
    @EqualsAndHashCode.Exclude
    private final Price price;

    @Getter
    private final ProductName productName;

    public ProductDefinition(ProductName productName, Price price) {
        if (productName == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.productName = productName;
        this.price = price;
    }

    public static ProductDefinition of(String productName, Money price) {
        return new ProductDefinition(new ProductName(productName), new Price(price));
    }

    public Money getPrice() {
        return this.price.productPrice;
    }
}
