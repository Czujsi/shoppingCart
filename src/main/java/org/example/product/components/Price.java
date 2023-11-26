package org.example.product.components;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.currency_exchange_money.Money;

@EqualsAndHashCode
@ToString
public class Price {

    public Money productPrice;

    public Price(Money productPrice) {
        this.productPrice = productPrice;
    }


}
