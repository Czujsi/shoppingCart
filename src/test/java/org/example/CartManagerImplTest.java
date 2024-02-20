package org.example;

import org.example.account.UserId;
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
    private final static UserId USER_ID = new UserId("1c821945-b6c0-4ce1-b073-dfc6c6da3694");
    private final static UserId USER_ID_2 = new UserId("8be75339-b73f-4521-b2ec-c67dc7aeb826");

    private CartRepository<UserId, Cart> repository;
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