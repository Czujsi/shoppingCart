package org.example.user_interfaces.text_interface.modules;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.store.Store;
import org.example.store.customer.Customer;
import org.example.store.employee.Employee;
import org.example.user.UserInput;

import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@RequiredArgsConstructor
public class Summary {
    private final Store store;
    private final Customer customer;
    private final Employee employee;
    private final Scanner scanner = new Scanner(System.in);

    public void options() {
        printOptions();
        while (true) {
            String input = UserInput.getInput(scanner);
            if (input.equals("remove")) {
                System.out.println("Type product name: ");
                String productName = UserInput.getInput(scanner);
                System.out.println("Type quantity of product that you want to remove: ");
                int quantity = Integer.parseInt(UserInput.getInput(scanner));
                customer.removeFromCart(productName, quantity);
                System.out.println("Product: " + productName + ", has been removed with quantity: " + quantity);
                printSummary();
            }
            if (input.equals("print")) {
                store.printCustomerDiscounts();
            }
            if (input.equals("sum")) {
                printSummary();
            }
            if (input.equals("search")) {
                System.out.println("Type product name that You searching for");
                employee.searchForItem(input);
            }
            if (input.equals("discount")) {
                System.out.println("Enter your discount code: ");
                String discountCode = UserInput.getInput(scanner);
                store.applyDiscountForCart(discountCode);
                printSummary();
            }
            if (input.equals("back")) {
                break;
            }
            if (input.equals("pay")) {
                System.out.println("Paying system");
                break;
            }
        }
    }

    private static void printOptions() {
        System.out.println("Summary options: ");
        System.out.println("For printing all your discount type print: ");
        System.out.println("For removing any product type: 'remove'");
        System.out.println("For summary of Your products type: 'sum'");
        System.out.println("For going back to shopping type: 'back'");
        System.out.println("For adding discount type: 'discount'");
        System.out.println("For paying type: 'pay'");
    }

    public void printSummary() {
        for (Map.Entry<ProductDefinition, Integer> entry : customer.getProducts().entrySet()) {
            out.println("Product: " +
                    entry.getKey().getName().getValue() +
                    ", quantity: " +
                    entry.getValue());
        }
        out.println(customer.overallSum());
    }
}
