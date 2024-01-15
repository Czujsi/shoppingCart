package org.example.product;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
public class Change<T> {
    private final LocalDateTime timestamp;
    private final ChangeType changeType;
    private final T value;

    public Change(ChangeType changeType, T value) {
        this.timestamp = LocalDateTime.now();
        this.changeType = changeType;
        this.value = value;
    }

    public ChangeType getType() {
        return changeType;
    }

    public enum ChangeType {
        PRICE,
        NAME
    }
}
