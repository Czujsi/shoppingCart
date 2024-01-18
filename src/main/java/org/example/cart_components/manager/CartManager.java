package org.example.cart_components.manager;

import org.example.cart_components.Cart;
import org.example.cart_components.UserId;

public interface CartManager {
    void addCart(Cart cart);

    void deleteCart(UserId userId);

    Cart getCartByUserId(UserId userId);
}
