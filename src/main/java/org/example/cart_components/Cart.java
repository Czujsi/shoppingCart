package org.example.cart_components;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.account.UserId;
import org.example.coupons.manager.CouponManager;
import org.example.coupons.discount.DiscountDefinition;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.Name;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@ToString
public class Cart {
    private final Map<ProductDefinition, Integer> products = new HashMap<>();

    private final Set<DiscountDefinition> discounts = new HashSet<>();

    Money amount;
    private final CouponManager couponManager;
    private final UserId userId;

    public void addItem(ProductDefinition productDefinition, int amount) {
        if (productDefinition == null)
            throw new RuntimeException("ProductDefinition cannot be null");
        if (amount == 0) {
            return;
        }
        if (amount < 0) {
            throw new RuntimeException("You cannot add negative value of productDefinition");
        }
        int oldAmount = quantityOf(productDefinition.getName().getValue());
        products.put(productDefinition, amount + oldAmount);
    }

    public void removeItem(String productName) {
        products.entrySet().removeIf(product -> product.getKey().getName().equals(new Name(productName)));
    }

    public boolean has(String productName) {
        return !products.keySet().stream()
                .map(ProductDefinition::getName)
                .filter(pn -> pn.equals(new Name(productName)))
                .toList().isEmpty();
    }

    public int quantityOf(String item) {
        return products.entrySet().stream()
                .filter(e -> e.getKey().getName().equals(new Name(item)))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(0);
    }

    public void removeQuantity(String productName, int productQuantity) {
        if (productQuantity <= 0) {
            throw new RuntimeException("You cannot remove negative value of products");
        }

        int oldAmount = quantityOf(productName);

        if (oldAmount < productQuantity) {
            throw new RuntimeException("You cannot remove quantity of productDefinition that is not in your cart");
        }

        ProductDefinition productDefinition = products.keySet().stream()
                .filter(p -> p.getName().getValue().equals(productName.toLowerCase()))
                .findFirst()
                .orElseThrow();
        products.replace(productDefinition, oldAmount - productQuantity);
    }

    public void applyDiscountCode(String code) {
        if (!couponManager.checkDiscountCode(code)) {
            throw new RuntimeException("This coupon code isn't valid");
        }
        DiscountDefinition couponForCode = couponManager.getCouponForCode(code);
        discounts.add(couponForCode);
    }

    public Money overallSum() {
        Money total = Money.of(BigDecimal.ZERO, Currency.PLN);
        for (Map.Entry<ProductDefinition, Integer> entry : products.entrySet()) {
            Integer amount = entry.getValue();
            Money price = applyProductDiscount(entry.getKey());
            Money priceByAmount = price.multiply(BigDecimal.valueOf(amount));
            total = total.add(priceByAmount);
        }
        if (discountCart(total).getAmount().compareTo(BigDecimal.ZERO) < 0){
            return Money.of(BigDecimal.ZERO, Currency.PLN);
        }
        return discountCart(total);
    }

    private Money applyProductDiscount(ProductDefinition key) {
        Money t = key.getPrice();
        for (DiscountDefinition discountDefinition : discounts) {
            t = discountDefinition.applyDiscountForProduct(t);
        }
        return t;
    }

    private Money discountCart(Money total) {
        Money t = total;
        for (DiscountDefinition discountDefinition : discounts) {
            t = discountDefinition.applyDiscountForCart(t);
        }
        return t;
//      return handleDiscount(total, discount -> discount::applyDiscountForCart);
    }

    private Money discountTransport(Money total) {
        Money t = total;
        for (DiscountDefinition discountDefinition : discounts) {
            t = discountDefinition.applyDiscountForTransport(t);
        }
        return t;
    }

    public void removeDiscount(String code) {
        discounts.removeIf(d -> d.getCode().equals(code));
    }

    public UserId getUserId() {
        return this.userId;
    }

    public Map<ProductDefinition, Integer> getProducts() {
        return products;
    }

    public Set<DiscountDefinition> getDiscounts() {
        return discounts;
    }
}

