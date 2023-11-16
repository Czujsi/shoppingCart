package org.example;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.currency_exchange.ExchangeRate;
import org.example.currency_exchange.Money;

@EqualsAndHashCode
@ToString
public class Price {

    Money productPrice;

    public Price(Money productPrice) {
        this.productPrice = productPrice;
    }


}
