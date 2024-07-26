package org.example.product.components;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.UUID;

@ToString
@Component
@EqualsAndHashCode
public class ProductId {
    @Getter
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
}
