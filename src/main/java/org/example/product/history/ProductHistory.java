package org.example.product.history;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.product.history.Change.ChangeType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.example.product.history.Change.ChangeType.NAME;
import static org.example.product.history.Change.ChangeType.PRICE;

@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@Component
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

    public List<Change<?>> getHistoryByNameAndChangeType(String productName, ChangeType changeType) {
        return filterChangesByNameAndType(productName, changeType);
    }

    private List<Change<?>> filterChangesByNameAndType(String productName, ChangeType changeType) {
        return productHistory.stream()
                .filter(c -> c.getValue().equals(productName))
                .filter(c -> c.getType().equals(changeType)).toList();
    }
}

