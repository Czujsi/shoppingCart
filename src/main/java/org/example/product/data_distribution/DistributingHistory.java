package org.example.product.data_distribution;

import org.example.product.ProductDefinition;
import org.example.product.history.Change;
import org.example.product.history.ProductHistory;

public class DistributingHistory implements SortingData {
    ProductHistory productHistory;
    public DistributingHistory(ProductHistory productHistory){
        this.productHistory = productHistory;
    }
    @Override
    public void filterDataByNameAndType(String productName, Change.ChangeType changeType) {
        productHistory.getHistoryByNameAndChangeType(productName, changeType);
    }

    @Override
    public void filterDataByPrice(String productName) {

    }

    @Override
    public void filterDataByDate(String productName) {

    }
}
