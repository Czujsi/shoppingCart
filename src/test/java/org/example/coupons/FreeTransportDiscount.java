package org.example.coupons;

import org.example.currency_exchange.Money;

public class FreeTransportDiscount implements DiscountCalculator {
    public FreeTransportDiscount(String discountCode, double value
    ) {
    }

    @Override
    public Money discount(Money amount) {
        return null;
    }
}
