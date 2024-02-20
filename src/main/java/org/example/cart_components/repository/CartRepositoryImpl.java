package org.example.cart_components.repository;

import org.example.account.UserId;
import org.example.cart_components.Cart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartRepositoryImpl implements CartRepository<UserId, Cart> {
    private final Map<UserId, ArrayList<Cart>> cartsRepository = new HashMap<>();

    @Override
    public void save(Cart cart) {
        UserId userId = cart.getUserId();
        ArrayList<Cart> userCarts = cartsRepository.getOrDefault(userId, new ArrayList<>());
        userCarts.add(cart);
        cartsRepository.put(userId, userCarts);
    }

    @Override
    public void delete(UserId byUserId) {
        cartsRepository.remove(byUserId);
    }

    @Override
    public boolean exists(UserId userId) {
        return cartsRepository.keySet().stream().anyMatch(c -> c.equals(userId));
    }

    @Override
    public Collection<Cart> get(UserId userId) {
        return cartsRepository.getOrDefault(userId, new ArrayList<>());
    }
}
