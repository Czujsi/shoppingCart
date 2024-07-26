package org.example.product.repository;

import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository<ID, TYPE> {
    void save(TYPE object);

    void delete(ID id);

    boolean exists(ID id);

    Optional<ProductDefinition> findById(ProductId productId);

    Collection<ProductDefinition> getAll();

    List<String> getIdForName(String name);

    Collection<ProductDefinition> refreshStock();
}
