package org.example.coupons;

import org.example.currency_exchange.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FlatPercentDiscount implements DiscountCalculator {
    private final BigDecimal multiplier;

    public FlatPercentDiscount(BigDecimal multiplier) {
        super();
        if (multiplier.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Flat percent discount can't be lower than 0 or 0");
        } else if (multiplier.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Flat percent discount can't be higher than 100%");
        }
        this.multiplier = BigDecimal.ONE.subtract(multiplier.divide(BigDecimal.valueOf(100.00),2, RoundingMode.HALF_DOWN));
    }

    @Override
    public Money discount(Money price) {
        return price.multiply(multiplier);
    }
}
