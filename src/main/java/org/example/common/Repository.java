package org.example.common;

public interface Repository<ID, TYPE> {
    void save(TYPE object);

    void delete(ID id);

    boolean exists(ID id);

    TYPE get(ID id);
}
