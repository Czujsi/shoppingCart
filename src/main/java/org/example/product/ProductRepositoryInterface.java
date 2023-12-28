package org.example.product;

import java.util.Collection;

public interface ProductRepositoryInterface<ID, TYPE>{
    void save(TYPE object);
    void delete(ID id);
    boolean exists(ID id);
    void update(ID id, TYPE object);
    TYPE get(ID id);
    Collection<ProductDefinition> getAll();
}
