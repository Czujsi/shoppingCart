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
    private Price price;

    @Getter
    private ProductName name;
    @Getter
    private final DateForProduct creationDate;
    private final ProductHistory productHistory = new ProductHistory();

    public ProductDefinition(ProductName name, Price price, DateForProduct localDateForProduct) {
        if (name == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.name = name;
        this.price = price;
        this.creationDate = localDateForProduct;
    }

    public static ProductDefinition of(String productName, Money price) {
        return new ProductDefinition(new ProductName(productName), new Price(price), new DateForProduct(LocalDate.now()));
    }

    public Money getPrice() {
        return this.price.productPrice;
    }

    public void updateName(String productName) {
        productHistory.append(new Change<>(Change.ChangeType.NAME, this.name));
        this.name = new ProductName(productName);
    }

    public void updatePrice(Money newPrice) {
        productHistory.append(new Change<>(Change.ChangeType.PRICE, this.price));
        this.price = new Price(newPrice);
    }
}
