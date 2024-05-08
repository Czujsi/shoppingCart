package org.example.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@EqualsAndHashCode
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

    @Override
    public String toString() {
        return "UserId: " +
                value;
    }

}
