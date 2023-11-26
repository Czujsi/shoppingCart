package org.example.product;

public interface ProductRepository <ID, TYPE>{
    void save(TYPE object);
    void delete(ID id);
    boolean exists(ID id);
    void update(ID id, TYPE object);



}
