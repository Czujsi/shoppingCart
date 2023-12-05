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
    private Price price;

    @Getter
    private final ProductName productName;

    public ProductDefinition(ProductName productName, Money price) {
        this(productName, new Price(price));
    }

    public ProductDefinition(ProductName productName, Price price) {
        if (productName == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.productName = productName;
        this.price = price;
    }

    public static ProductDefinition of(String productName, Money price){
        return new ProductDefinition(new ProductName(productName), new Price(price));
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
//        ProductDefinition other = (ProductDefinition) o;
//        return Objects.equals(this.productName.toLowerCase(), other.productName.toLowerCase());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productName);
//    }


}
