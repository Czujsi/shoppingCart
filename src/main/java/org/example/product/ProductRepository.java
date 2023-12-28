package org.example.product;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ToString
@EqualsAndHashCode
public class ProductRepository implements ProductRepositoryInterface<String, ProductDefinition> {
    private final Map<String, ProductDefinition> productRepository = new HashMap<>();

    @Override
    public void save(ProductDefinition object) {
        productRepository.put(object.getProductName().getValue(), object);
    }

    @Override
    public void delete(String name) {
        productRepository.remove(name);
    }


    @Override
    public boolean exists(String name) {
        return productRepository.containsKey(name);
    }

    @Override
    public void update(String oldName, ProductDefinition productDefinition) {
        productRepository.replace(oldName, productDefinition);
    }

    public ProductDefinition get(String name) {
        return productRepository.get(name);
    }

    @Override
    public Collection<ProductDefinition> getAll() {
        return productRepository.values();
    }
}
