package org.example.product.components;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class ProductId {
    private final UUID value;

    public ProductId(String productId) {
        this.value = UUID.fromString(productId);
    }

    public ProductId() {
        this.value = UUID.randomUUID();
    }

    public static ProductId createId() {
        return new ProductId();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
