package org.example.product.history;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.product.history.Change;
import org.example.product.history.Change.ChangeType;

import java.util.*;

import static org.example.product.history.Change.ChangeType.*;

@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class ProductHistory {
    private final List<Change<?>> productHistory = new ArrayList<>();
    public void append(Change<?> change) {
        productHistory.add(change);
    }

    public List<Change<?>> versionControl(ChangeType changeType) {
        return switch (changeType) {
            case NAME -> filterChangesByType(NAME);
            case PRICE -> filterChangesByType(PRICE);
        };
    }

    private List<Change<?>> filterChangesByType(ChangeType type) {
        return productHistory.stream()
                .filter(c -> c.getType().equals(type))
                .toList();
    }
}

