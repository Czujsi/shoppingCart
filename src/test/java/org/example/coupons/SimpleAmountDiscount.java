package org.example.coupons;

import org.example.currency_exchange.Money;


public class SimpleAmountDiscount implements DiscountCalculator {
    private final Money value;

    public SimpleAmountDiscount(Money of) {
        this.value = of;
    }

    @Override
    public Money discount(Money oldPrice) {
        return oldPrice.subtract(value);
    }
}
