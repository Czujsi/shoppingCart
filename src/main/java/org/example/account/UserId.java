package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;
@EqualsAndHashCode
public class UserId {
    @Getter
    private final UUID value;

    public UserId(String productId) {
        this.value = UUID.fromString(productId);
    }

    public UserId() {
        this.value = UUID.randomUUID();
    }

    public static UserId createId() {
        return new UserId();
    }

    @Override
    public String toString() {
        return "UserId: " +
                value;
    }
}
