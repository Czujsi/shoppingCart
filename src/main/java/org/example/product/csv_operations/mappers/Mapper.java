package org.example.product.csv_operations.mappers;

public interface Mapper<TYPE, REPRESENTATION>{
    REPRESENTATION deserialize(TYPE type);
    TYPE serialize(REPRESENTATION representation);
}
