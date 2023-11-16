package org.example.coupons;

import org.example.currency_exchange.Money;

public interface DiscountCalculator {
    Money discount(Money amount);
}
