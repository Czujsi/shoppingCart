package org.example.user_interfaces.text_interface;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.manager.ProductManager;
import org.example.store.customer.Customer;
import org.example.store.employee.Employee;
import org.example.user.UserInput;

import java.util.List;
import java.util.Scanner;

import static java.text.MessageFormat.format;
@RequiredArgsConstructor
public class ChoosingItems {
    private final Customer customer;
    private final Employee employee;
    private final ProductManager productManager;
    private final SummaryOptions summaryOptions;
    private final Scanner scanner = new Scanner(System.in);
    public void option() {
        System.out.println("Here are items that You can buy in our shop: ");
        printStock();
        System.out.println("For adding products to your cart type product name");
        System.out.println("To exit type: 'exit'");
        System.out.println("For summary type: 'summary'");
        while (true) {
            String input = UserInput.getInput(scanner);
            if (input.equals("exit")) {
                System.out.println("Thank You for visiting, have a nice day");
                break;
            }
            if (input.equals("summary ")) {
                summaryOptions.options();
                break;
            }
            if (input.equals("search")) {
                System.out.println("Type product name that You searching for");
                employee.searchForItem(input);
            }
            customer.addToCart(getIdByInput(input));
        }
    }
    private List<ProductDefinition> getAllProducts() {
        return productManager.getAllProducts().stream().toList();
    }

    private void printStock() {
        for (int i = 0; i < getAllProducts().size(); i++) {
            System.out.println(getString(i));
        }
    }

    private String getString(int i) {
        return format("{0} -> Product: {1}, price {2}"
                , i
                , getAllProducts().get(i).getName().getValue()
                , getAllProducts().get(i).getPrice());
    }
    private int getNumber(String userInput) {
        return Integer.parseInt(userInput);
    }

    private String getIdByInput(String userInput) {
        ProductId id = getAllProducts().get(getNumber(userInput)).getProductId();
        return id.toString();
    }
}
