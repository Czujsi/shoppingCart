package org.example.store.employee;

public interface Employee {
    void addToStock();

    boolean checkIfExist(String input);

    void updateOnStock();

    void removeFromStock(String input);

    void addFlatPercentDiscount();

    void searchForItem(String input);
}
