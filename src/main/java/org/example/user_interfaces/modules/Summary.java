package org.example.user_interfaces.modules;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.store.Store;
import org.example.user_interfaces.customer.Customer;
import org.example.user_interfaces.modules.employee.Employee;
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
            out.println("help -> for help");
            String input = UserInput.getInput("summary", scanner);
            if (input.equals("remove")) {
                removeProduct();
            } else if (input.equals("discount")) {
                store.printCustomerDiscounts();
            } else if (input.equals("sum")) {
                printSummary();
            } else if (input.equals("coupon")) {
                applyCoupon();
            } else if (input.equals("back")) {
                break;
            } else if (input.equals("help")) {
                printOptions();
            } else if (input.equals("pay")) {
                System.out.println("Paying system");
                break;
            } else {
                out.println("Wrong command, try again");
            }
        }
    }

    private void applyCoupon() {
        System.out.println("Enter your discount code: ");
        String discountCode = UserInput.getInput(scanner);
        store.applyDiscountForCart(discountCode);
        printSummary();
    }

    private void removeProduct() {
        System.out.println("Type product name: ");
        String productName = UserInput.getInput(scanner);
        System.out.println("Type quantity of product that you want to remove: ");
        int quantity = Integer.parseInt(UserInput.getInput(scanner));
        customer.removeFromCart(productName, quantity);
        System.out.println("Product: " + productName + ", has been removed with quantity: " + quantity);
        printSummary();
    }

    private static void printOptions() {
        System.out.println("Summary options: ");
        System.out.println("\t\u001B[1msum\u001B[0m      -> for summary of your products");
        System.out.println("\t\u001B[1mremove\u001B[0m   -> for removing any product");
        System.out.println("\t\u001B[1mcoupon\u001B[0m   -> for adding coupon");
        System.out.println("\t\u001B[1mdiscount\u001B[0m -> for printing all your active discounts");
        System.out.println("\t\u001B[1mpay\u001B[0m      -> for paying");
        System.out.println("\t\u001B[1mback\u001B[0m     -> for going back");
    }

    public void printSummary() {
        for (Map.Entry<ProductDefinition, Integer> entry : customer.getProductsMap().entrySet()) {
            out.println("Product: " +
                    entry.getKey().getName().getValue() +
                    ", quantity: " +
                    entry.getValue());
        }
        out.println(customer.overallSum());
    }
}
