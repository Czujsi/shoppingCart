package org.example.product.converters;

import org.example.product.ProductDefinition;

import java.util.List;

public interface FileConverter {
    List<ProductDefinition> convertFromFile(String filePath);

    void convertToFile();
}
