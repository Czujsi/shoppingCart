package org.example.product;

import org.example.currency_exchange_money.Money;

import java.math.BigDecimal;
import java.util.Collection;

public interface ProductRepository<ID, TYPE>{
    void save(TYPE object);
    void delete(ID id);
    boolean exists(ID id);
    TYPE get(ID id);
    Collection<ProductDefinition> getAll();
}
