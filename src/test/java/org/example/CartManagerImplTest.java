package org.example;

import org.example.cart_components.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartManagerImplTest {
    private final static UserId USER_ID = new UserId(1233412L);
    private final static UserId USER_ID_2 = new UserId(122233412L);

    private CartInMemoryRepository cartInMemoryRepository;
    private CartManager cartManager;

    @BeforeEach
    void setUp() {
        cartInMemoryRepository = new CartInMemoryRepository();
        cartManager = new CartManagerImpl(cartInMemoryRepository);
    }

    @Test
    void checkingIfAddCartMethodWorksProperly() {
        cartManager.addCart(cartForUser(USER_ID));

        assertThat(cartInMemoryRepository.exists(USER_ID)).isTrue();
    }

    @Test
    void checkingIfAddCartMethodWorksProperly2() {
        assertThat(cartInMemoryRepository.exists(USER_ID_2)).isFalse();
    }

    @Test
    void checkingIfDeleteCartMethodWorksProperly() {
        cartManager.addCart(cartForUser(USER_ID));

        cartManager.deleteCart(USER_ID);

        assertThat(cartInMemoryRepository.exists(USER_ID)).isFalse();
    }

    private Cart cartForUser(UserId userId) {
        Cart cart = mock(Cart.class);
        when(cart.getUserId()).thenReturn(userId);
        return cart;
    }
}