package org.example.cart_components;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.product.components.ProductId;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@EqualsAndHashCode
@ToString
public class CartId {
    @Getter
    private final UUID uuid;

    public CartId(String productId) {
        this.uuid = UUID.fromString(productId);
    }

    public CartId() {
        this.uuid = UUID.randomUUID();
    }

    public static ProductId createId() {
        return new ProductId();
    }
}
