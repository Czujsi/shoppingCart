package org.example.product;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;

@ToString
@EqualsAndHashCode
public class ProductInMemoryRepository implements ProductRepository<String, ProductDefinition>{
    ProductDefinition productDefinition;
    private final HashMap<String, ProductDefinition> productRepository = new HashMap<>();
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
    public void update(String oldName, ProductDefinition object) {
        productRepository.replace(oldName, object);
    }

    public ProductDefinition get(String name) {
        return productRepository.get(name);
    }
}
