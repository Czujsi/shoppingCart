package org.example.user_interfaces.text_interface.modules;

import lombok.RequiredArgsConstructor;
import org.example.product.manager.ProductManager;
import org.example.user_interfaces.text_interface.customer.Customer;
import org.example.user.UserInput;
import org.example.user_interfaces.text_interface.modules.employee.Employee;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SuppressWarnings("IfCanBeSwitch")
@Component
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
            System.out.println(customer.getId());
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
            } else if (input.equals("create")) {
                customer.createCart();
            } else if (input.equals("change")) {
                String number = UserInput.getInput("Type number", scanner);
                customer.chooseCart(number);
            } else if (input.equals("save")) {
                customer.saveCart();
            } else if (input.equals("get")) {
                customer.printCarts();
            } else if (input.equals("delete")) {
                customer.deleteCart();
            } else {
                System.out.println("Wrong command, try again.");
            }
            System.out.println();
        }
    }

    private void printOptions() {
        stockOutput.printStock();
        System.out.println("\tadd     -> for adding items to cart");
        System.out.println("\tsummary -> for going to summary section");
        System.out.println("\tdelete  -> for deleting a cart");
        System.out.println("\tchange  -> for changing your cart");
        System.out.println("\tcreate  -> for creating new cart");
        System.out.println("\tget     -> for printing all your carts");
        System.out.println("\tsave    -> for saving your cart");
        System.out.println("\tback    -> for going back");
    }
}
