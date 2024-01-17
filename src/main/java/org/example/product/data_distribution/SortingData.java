package org.example.product.data_distribution;

import org.example.product.history.Change;

public interface SortingData {
    void filterDataByNameAndType(String productName, Change.ChangeType changeType);
    void filterDataByPrice(String productName);
    void filterDataByDate(String productName);
}
