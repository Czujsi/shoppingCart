package org.example.store;

import org.example.currency_exchange_money.Money;
import org.example.product.ProductDefinition;

import java.util.Map;

public interface Customer {

    void addToCart(String input);
    void removeFromCart(String input);
    void checkPrice();

    Map<ProductDefinition, Integer> getCart();

    Money overallSum();
}
