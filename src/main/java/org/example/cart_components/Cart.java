package org.example.cart_components;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.coupons.CouponManager;
import org.example.coupons.DiscountDefinition;
import org.example.currency_exchange_money.Currency;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductName;

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
        int oldAmount = quantityOf(productDefinition.getProductName().getValue());
        products.put(productDefinition, amount + oldAmount);
        System.out.println();

    }

    public void removeItem(String productName) {
        products.entrySet().removeIf(product -> product.getKey().getProductName().equals(new ProductName(productName)));
    }

    public boolean has(String productName) {
        return !products.keySet().stream()
                .map(ProductDefinition::getProductName)
                .filter(pn -> pn.equals(new ProductName(productName)))
                .toList().isEmpty();
    }

    public int quantityOf(String item) {
        return products.entrySet().stream()
                .filter(e -> e.getKey().getProductName().equals(new ProductName(item)))
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

        ProductDefinition productDefinition = products.keySet().stream().filter(p -> p.getProductName().getValue().equals(productName.toLowerCase())).findFirst().orElseThrow();
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
            Money priceByAmount = price.multiply(amount);
            total = total.add(priceByAmount);
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

    public void removeDiscount(String code) {
        discounts.removeIf(d -> d.getCode().equals(code));
    }

    public UserId getUserId() {
        return this.userId;
    }

    public Map<ProductDefinition, Integer> writeOutProducts() {
        return products;
    }
}

