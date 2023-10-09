package org.example;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Product {
    @EqualsAndHashCode.Exclude
    @Getter
    private final Price price;


    @Getter
    private final ProductName productName;


    public Product(String productName, Price price1) {
        if (productName == null) {
            throw new RuntimeException("You cannot add or remove product with null name");
        }

        this.productName = new ProductName(productName);
        this.price = price1;
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
