package org.example.user_interfaces.text_interface.customer;

import org.example.cart_components.Cart;
import org.example.coupons.discount.DiscountDefinition;
import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public interface Customer {

    void addToCart(String input);

    void removeFromCart(String input, int quantity);

    void removeAll(String input);

    List<ProductDefinition> getProducts();

    Money overallSum();

    void applyDiscount(String input);

    Set<DiscountDefinition> printDiscounts();

    Cart getCart();

    String getId();

    void saveCart();

    void createCart();

    void deleteCart();

    Collection<Cart> getCarts();

    Cart chooseCart(String input);

    void printCarts();

    Map<ProductDefinition, Integer> getProductsMap();
}
