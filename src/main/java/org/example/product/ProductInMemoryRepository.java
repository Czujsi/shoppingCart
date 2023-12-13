package org.example.product;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@EqualsAndHashCode
public class ProductInMemoryRepository implements ProductRepository<String, ProductDefinition> {
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

    public void writeOutProducts() {
        for (Map.Entry<String, ProductDefinition> entry : productRepository.entrySet()) {
            System.out.println("Product: " +
                    entry.getValue().getProductName().getValue() +
                    ", price: " +
                    entry.getValue().getPrice().getAmount().toString());
        }
    }
}
