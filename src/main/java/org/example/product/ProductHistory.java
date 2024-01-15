package org.example.product;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.product.Change.ChangeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public Optional<List<Change<?>>> versionControl(ChangeType changeType) {
        List<Change<?>> changes = new ArrayList<>(productHistory);
        switch (changeType) {
            case NAME -> {
                List<Change<?>> nameChanges = (changes.stream()
                        .filter(c -> c.getType() == ChangeType.NAME)
                        .toList());
                return Optional.of(nameChanges);
            }
            case PRICE -> {
                List<Change<?>> priceChanges = changes.stream()
                        .filter(c -> c.getType() == ChangeType.PRICE)
                        .toList();
                return Optional.of(priceChanges);
            }
        }
        return Optional.empty();
    }
}

