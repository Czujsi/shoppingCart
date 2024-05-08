package org.example.coupons.discount.type;

import org.example.coupons.discount.DiscountCalculator;
import org.example.currency_exchange_money.Money;

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
