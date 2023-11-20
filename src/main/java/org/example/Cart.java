package org.example;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.coupons.CouponManager;
import org.example.coupons.DiscountDefinition;
import org.example.currency_exchange.Currency;
import org.example.currency_exchange.Money;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@ToString
public class Cart {

    private final Map<Product, Integer> products = new HashMap<>();

    private final Set<DiscountDefinition> discounts = new HashSet<>();

    Money amount;
    private final CouponManager couponManager;

    public void addItem(Product product, int amount) {
        if (product == null)
            throw new RuntimeException("Product cannot be null");
        if (amount == 0) {
            return;
        }
        if (amount < 0) {
            throw new RuntimeException("You cannot add negative value of product");
        }
        int oldAmount = quantityOf(product.getProductName().getValue());
        products.put(product, amount + oldAmount);

    }

    public void removeItem(String productName) {
        products.entrySet().removeIf(product -> product.getKey().getProductName().equals(new ProductName(productName)));
    }

    public boolean has(String productName) {
        return !products.keySet().stream()
                .map(Product::getProductName)
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
            throw new RuntimeException("You cannot remove quantity of product that is not in your cart");
        }

        Product product = products.keySet().stream().filter(p -> p.getProductName().getValue().equals(productName.toLowerCase())).findFirst().orElseThrow();
        products.replace(product, oldAmount - productQuantity);
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
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Integer amount = entry.getValue();
            Money price = applyProductDiscount(entry.getKey());
            Money priceByAmount = price.multiply(amount);
            total = total.add(priceByAmount);
        }
        return discountCart(total);
    }

    private Money applyProductDiscount(Product key) {
        Money t = key.getPrice();
        for (DiscountDefinition discountDefinition : discounts) {
            t = discountDefinition.applyDiscountForProduct(t);
        }
        return t;

//      return handleDiscount(key.getPrice(), discount -> discount::applyDiscountForProduct);
    }

    private Money discountCart(Money total) {
        Money t = total;
        for (DiscountDefinition discountDefinition : discounts) {
            t = discountDefinition.applyDiscountForCart(t);
        }
        return t;
//      return handleDiscount(total, discount -> discount::applyDiscountForCart);
    }

    public void removeDiscountFromCart(String code){
        for (DiscountDefinition discountDefinition : discounts){
            discounts.removeIf(d -> discountDefinition.getCode().equals(code));
        }
    }

//    private Money handleDiscount(Money initial, Function<DiscountDefinition, Function<Money, Money>> f) {
//        Money t = initial;
//        for (DiscountDefinition discountDefinition : discounts) {
//            t = f.apply(discountDefinition).apply(t);
//        }
//        return t;
//    }
}

