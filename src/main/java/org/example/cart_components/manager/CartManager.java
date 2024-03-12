package org.example.cart_components.manager;

import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface CartManager {
    Cart addCart(Cart cart);

    void deleteCart(UserId userId);

    Collection<Cart> getCartByUserId(UserId userId);
}
