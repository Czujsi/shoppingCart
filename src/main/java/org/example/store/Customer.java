package org.example.store;

import org.example.cart_components.Cart;
import org.example.coupons.DiscountDefinition;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;

import java.util.Map;
import java.util.Set;

public interface Customer {

    void addToCart(String input);
    void removeFromCart(String input, int quantity);
    void removeAll(String input);
    void checkPrice();

    Map<ProductDefinition, Integer> getProducts();

    Money overallSum();

    void applyDiscount(String input);

    Set<DiscountDefinition> printDiscounts();

    Cart getCart();
}
