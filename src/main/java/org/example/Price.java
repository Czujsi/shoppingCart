package org.example;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Price {

    private final double productPrice;

    public Price(double productPrice) {
        this.productPrice = productPrice;
    }


}
