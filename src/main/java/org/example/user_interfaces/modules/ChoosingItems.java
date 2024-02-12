package org.example.user_interfaces.modules;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.manager.ProductManager;
import org.example.store.customer.Customer;
import org.example.user_interfaces.modules.employee.Employee;
import org.example.user.UserInput;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static java.text.MessageFormat.format;

@SuppressWarnings("IfCanBeSwitch")
@RequiredArgsConstructor
public class ChoosingItems {
    public static final String COMMAND_BACK = "back";
    public static final String COMMAND_SUMMARY = "summary";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_HELP = "help";
    private final Customer customer;
    private final Employee employee;
    private final ProductManager productManager;
    private final Summary summary;
    private final StockOutput stockOutput;
    private final Scanner scanner = new Scanner(System.in);

    public void option() {
        stockOutput.printStock();
        while (true) {
            System.out.println("Type help for help");
            String input = UserInput.getInput("choosing items", scanner);
            if (input.equals(COMMAND_BACK)) {
                break;
            } else if (input.equals(COMMAND_SUMMARY)) {
                summary.options();
            } else if (input.equals(COMMAND_ADD)) {
                String productNumber = UserInput.getInput("choosing items>product number", scanner);
                customer.addToCart(stockOutput.getIdByInput(productNumber));
            } else if (input.equals(COMMAND_HELP)) {
                printOptions();
            } else {
                System.out.println("Wrong command, try again.");
            }
            System.out.println();
        }
    }

    private void printOptions() {
        stockOutput.printStock();
        System.out.println("\tadd -> for adding items to cart");
        System.out.println("\tsummary -> for going to summary section");
        System.out.println("\tback -> for going back");
    }

    private List<ProductDefinition> getAllProducts() {
        return productManager.getAllProducts().stream().toList();
    }

}
