package org.example.cart_components;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EqualsAndHashCode
@ToString
public class CartId {
    @Getter
    private final UUID uuid;

    public CartId(String cartId) {
        this.uuid = UUID.fromString(cartId);
    }

    public CartId() {
        this.uuid = UUID.randomUUID();
    }

    public static CartId createId() {
        return new CartId();
    }
}
