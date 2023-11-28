package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class UserId {
    private final Long value;

    public UserId(Long userId) {
        this.value = userId;
    }
}
