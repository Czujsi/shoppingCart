package org.example.coupons;

import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
@EqualsAndHashCode
public class DiscountInMemoryRepository implements Repository<String, DiscountDefinition>{
    private final Map<String, DiscountDefinition> repository = new HashMap<>();
    @Override
    public void save(DiscountDefinition object) {
        repository.put(object.getCode(), object);
    }

    @Override
    public void delete(String code) {
        repository.remove(code);
    }

    @Override
    public DiscountDefinition get(String code) {
        return repository.get(code);
    }

    @Override
    public boolean exists(String code) {
        return repository.keySet().stream().anyMatch(c -> c.contains(code));
    }

}
