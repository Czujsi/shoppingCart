package org.example.product;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.currency_exchange_money.Money;
import org.example.product.components.CreationDate;
import org.example.product.components.Name;
import org.example.product.components.Price;
import org.example.product.components.ProductId;
import org.example.product.history.Change;
import org.example.product.history.ProductHistory;

import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDefinition {
    private Price price;
    @Getter
    private Name name;
    @Getter
    private final CreationDate creationDate;
    private final ProductHistory productHistory = new ProductHistory();
    @Getter
    @EqualsAndHashCode.Include
    private ProductId productId = ProductId.createId();

    public ProductDefinition(Name name, Price price, CreationDate creationDate) {
        if (name == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }

    public ProductDefinition(Name name, Price price, CreationDate creationDate, ProductId productId) {
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
        this.productId = productId;
    }

    public static ProductDefinition of(String productName, Money price) {
        return new ProductDefinition(new Name(productName), new Price(price), new CreationDate(LocalDate.now()));
    }

    public static ProductDefinition of(String productName, Money price, String productId) {
        return new ProductDefinition(new Name(productName), new Price(price), new CreationDate(LocalDate.now()), new ProductId(productId));
    }

    @Override
    public String toString() {
        return "Name= " + name.getValue() +
                ", Price= " + price.productPrice.getAmount().toString();
    }

    public Money getPrice() {
        return this.price.productPrice;
    }

    public void updateName(String productName) {
        productHistory.append(new Change<>(Change.ChangeType.NAME, this.name));
        this.name = new Name(productName);
    }

    public void updatePrice(Money newPrice) {
        productHistory.append(new Change<>(Change.ChangeType.PRICE, this.price));
        this.price = new Price(newPrice);
    }
}
