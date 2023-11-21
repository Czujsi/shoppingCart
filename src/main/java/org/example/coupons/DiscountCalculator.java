package org.example.coupons;

import org.example.currency_exchange_money.Money;

public interface DiscountCalculator {
    Money discount(Money amount);
}
