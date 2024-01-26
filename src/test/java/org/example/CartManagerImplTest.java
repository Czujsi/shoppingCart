package org.example;

import org.example.cart_components.*;
import org.example.cart_components.manager.CartManager;
import org.example.cart_components.manager.CartManagerImpl;
import org.example.cart_components.repository.CartRepository;
import org.example.cart_components.repository.CartRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartManagerImplTest {
    private final static UserId USER_ID = new UserId(1233412L);
    private final static UserId USER_ID_2 = new UserId(122233412L);

    private CartRepository repository;
    private CartManager manager;

    @BeforeEach
    void setUp() {
        repository = new CartRepositoryImpl();
        manager = new CartManagerImpl(repository);
    }

    @Test
    void checkingIfAddCartMethodWorksProperly() {
        manager.addCart(cartForUser(USER_ID));

        assertThat(repository.exists(USER_ID)).isTrue();
    }

    @Test
    void checkingIfAddCartMethodWorksProperly2() {
        assertThat(repository.exists(USER_ID_2)).isFalse();
    }

    @Test
    void checkingIfDeleteCartMethodWorksProperly() {
        manager.addCart(cartForUser(USER_ID));

        manager.deleteCart(USER_ID);

        assertThat(repository.exists(USER_ID)).isFalse();
    }

    private Cart cartForUser(UserId userId) {
        Cart cart = mock(Cart.class);
        when(cart.getUserId()).thenReturn(userId);
        return cart;
    }
}