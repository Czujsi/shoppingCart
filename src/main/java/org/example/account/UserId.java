package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@ToString
public class UserId {
    @Getter
    private final UUID value;

    public UserId(String userId) {
        this.value = UUID.fromString(userId);
    }

    public UserId() {
        this.value = UUID.randomUUID();
    }

    public static UserId createId() {
        return new UserId();
    }
}
