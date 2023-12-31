package org.example.cart_components;

public interface CartManager {
    void addCart(Cart cart);

    void deleteCart(UserId userId);

    Cart getCartByUserId(UserId userId);
}
