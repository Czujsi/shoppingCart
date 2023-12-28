package org.example.product;

public interface ProductRepositoryInterface<ID, TYPE>{
    void save(TYPE object);
    void delete(ID id);
    boolean exists(ID id);
    void update(ID id, TYPE object);
    TYPE get(ID id);
    void getAll();
}
