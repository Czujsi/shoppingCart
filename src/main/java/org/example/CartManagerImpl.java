package org.example;

import lombok.AllArgsConstructor;

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
