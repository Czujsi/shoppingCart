package org.example.coupons.discount;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.coupons.discount.type.DiscountType;
import org.example.currency_exchange_money.Money;

import java.util.Map;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class DiscountDefinition {
    @EqualsAndHashCode.Include
    @Getter
    private final String code;

    private final Map<DiscountType, DiscountCalculator> calculators;

    public Money applyDiscountForCart(Money amount) {
        return applyDiscount(amount, DiscountType.Cart);
    }

    public Money applyDiscountForProduct(Money amount) {
        return applyDiscount(amount, DiscountType.Product);
    }

    public Money applyDiscountForTransport(Money amount) {
        return applyDiscount(amount, DiscountType.Transport);
    }

    private Money applyDiscount(Money amount, DiscountType type) {
        DiscountCalculator calculator = this.calculators.get(type);

        return calculator != null ? calculator.discount(amount) : amount;
    }

    public Object getDiscountValue(String code) {
        return calculators;
    }
}
