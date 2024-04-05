package org.example.user_interfaces.text_interface.modules.employee;

import org.springframework.stereotype.Component;

@Component
public interface Employee {
    void addToStock();

    boolean checkIfExist(String input);

    void updateOnStock();

    void removeFromStock(String input);

    void addFlatPercentDiscount();

    void searchForItem(String input);
}
