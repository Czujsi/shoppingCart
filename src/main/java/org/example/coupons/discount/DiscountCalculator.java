package org.example.coupons.discount;

import org.example.currency_exchange_money.Money;

public interface DiscountCalculator {
    Money discount(Money amount);
}
