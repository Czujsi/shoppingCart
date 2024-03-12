package org.example.cart_components.manager;

import lombok.AllArgsConstructor;
import org.example.account.UserId;
import org.example.cart_components.Cart;
import org.example.cart_components.repository.CartRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class CartManagerImpl implements CartManager {
    private final CartRepository<UserId, Cart> cartRepository;

    @Override
    public Cart addCart(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void deleteCart(UserId byUserId) {
        cartRepository.delete(byUserId);
    }

    @Override
    public Collection<Cart> getCartByUserId(UserId userId) {
        return cartRepository.get(userId);
    }
}
