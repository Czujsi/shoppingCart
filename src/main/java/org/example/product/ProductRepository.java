package org.example.product;

import org.example.product.components.ProductId;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository<ID, TYPE>{
    void save(TYPE object);
    void delete(ID id);
    boolean exists(ID id);
    Optional<ProductDefinition> findById(ProductId productId);
    Collection<ProductDefinition> getAll();
    Collection<ProductDefinition> getIdForName(String name);
}
