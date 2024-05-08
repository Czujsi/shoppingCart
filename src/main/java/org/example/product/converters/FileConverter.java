package org.example.product.converters;

import org.example.product.ProductDefinition;

import java.io.IOException;
import java.util.List;

public interface FileConverter {
    List<ProductDefinition> convertFromFile(String filePath) throws IOException;

    void convertToFile();
}
