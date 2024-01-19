package org.example.currency_exchange_money;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Money implements Comparable<Money> {
    private BigDecimal amount;
    private Currency currency;

    @Deprecated
    public static Money of(double v, Currency currency) {
        return Money.of(BigDecimal.valueOf(v), currency);
    }

    public static Money of(BigDecimal v, Currency pln) {
        return new Money(v, pln);
    }

    public Money add(Money priceByAmount) {
        validateCurrencyIsMatching(priceByAmount);
        return new Money(amount.add(priceByAmount.amount), currency);
    }

    public Money subtract(Money other) {
        validateCurrencyIsMatching(other);
        return new Money(amount.subtract(other.amount), currency);
    }

    private void validateCurrencyIsMatching(Money priceByAmount) {
        if (!currency.equals(priceByAmount.getCurrency())) {
            throw new IllegalArgumentException("You cannot add different currencies");
        }
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(amount.multiply(multiplier), currency);
    }

    @Override
    public int compareTo(Money o) {
        boolean equals = this.getCurrency().equals(o.getCurrency());
        if (!equals) {
            return -1;
        }
        return this.getAmount().compareTo(o.getAmount());
    }

    @Override
    public String toString() {
        return getAmount() + " " + getCurrency();
    }
}
