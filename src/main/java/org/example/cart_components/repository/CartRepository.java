package org.example.cart_components.repository;

import org.example.cart_components.Cart;

import java.util.Collection;

public interface CartRepository <ID, TYPE> {
    void save(TYPE object);

    void delete(ID id);

    boolean exists(ID id);

    Collection<Cart> get(ID id);
}
