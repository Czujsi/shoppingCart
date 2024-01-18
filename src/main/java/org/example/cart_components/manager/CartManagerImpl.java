package org.example.cart_components.manager;

import lombok.AllArgsConstructor;
import org.example.cart_components.Cart;
import org.example.cart_components.repository.CartRepository;
import org.example.cart_components.UserId;

@AllArgsConstructor
public class CartManagerImpl implements CartManager {
    private final CartRepository cartRepository;

    @Override
    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(UserId byUserId) {
        cartRepository.delete(byUserId);
    }

    @Override
    public Cart getCartByUserId(UserId userId) {
        return cartRepository.get(userId);
    }
}
