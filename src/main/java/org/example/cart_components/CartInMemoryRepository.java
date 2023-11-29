package org.example.cart_components;

import java.util.HashMap;
import java.util.Map;

public class CartInMemoryRepository implements CartRepository {
    private final Map<UserId, Cart> cartsRepository = new HashMap<>();

    @Override
    public void save(Cart object) {
        cartsRepository.put(object.getUserId(), object);
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
    public Cart get(UserId userId) {
        return cartsRepository.get(userId);
    }
}
