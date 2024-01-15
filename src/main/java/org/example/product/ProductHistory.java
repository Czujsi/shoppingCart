package org.example.product;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.product.Change.ChangeType;

import java.util.*;

import static org.example.product.Change.ChangeType.*;

@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class ProductHistory {
    private final List<Change<?>> productHistory = new ArrayList<>();

    //ublic void saveChange(String productName, ProductDefinition oldValue, ProductDefinition newValue){
    //   Change change = new Change(productName, oldValue, newValue);
    //   productHistory.add(change);
    //
    //ublic List<Change<?>> getChanges() {
    //   return new ArrayList<>(productHistory);
    //
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

