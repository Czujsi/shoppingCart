package org.example.cart_components.manager;

import org.example.account.UserId;
import org.example.cart_components.Cart;

import java.util.Collection;

public interface CartManager {
    Cart addCart(Cart cart);

    void deleteCart(UserId userId);

    Collection<Cart> getCartByUserId(UserId userId);
}
