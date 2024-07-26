package org.example.fileUtilities;

import org.example.product.components.ProductId;

import java.io.File;
import java.util.Collection;
import java.util.List;

public interface FileUtil {
    boolean checkIfFileExist(File file);

    void createFile(String fileName);

    void deleteFile(String filePath);

    void deleteFromFile(File file, ProductId productId);

    void writeToFile(File file, String productsAsText);

    void writeCollectionToFile(File file, Collection<String> productDefinitions);

    List<String> readFile(File file);
}
