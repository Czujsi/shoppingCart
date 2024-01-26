package org.example.user_interfaces.text_interface;

import lombok.RequiredArgsConstructor;
import org.example.product.ProductDefinition;
import org.example.product.components.ProductId;
import org.example.product.manager.ProductManager;
import org.example.store.Store;
import org.example.store.customer.Customer;
import org.example.store.employee.Employee;
import org.example.user.UserInput;
import org.example.user_interface.UserInterface;

import java.util.List;
import java.util.Scanner;

import static java.text.MessageFormat.format;

@RequiredArgsConstructor
public class TextInterface implements UserInterface {
    private final Store store;
    private final Customer customer;
    private final Employee employee;
    private final ProductManager productManager;
    private final Scanner scanner = new Scanner(System.in);


    public void run() {
        label:
        while (true) {
            System.out.println("Welcome in our shop user");
            System.out.println();
            System.out.println("If you want to log-in, type: log");
            System.out.println("If you want to register, type: register");
            System.out.println("If you want to shop without account, type: shop");
            System.out.println("If you want to exit, type: exit");
            String input = UserInput.getInput(scanner);
            switch (input) {
                case "log":
                    System.out.println("Logging system is currently unapproachable, sorry for trouble");
                    break;
                case "register":
                    System.out.println("Register system is currently unapproachable, sorry for trouble");
                    break;
                case "shop":
                    choosingItems();
                    break;
                case "exit":
                    System.out.println("Thank you for visiting, have a nice day");
                    break label;
                case "add":
                    addItemsToStock();
                    break;
                case "add1":
                    addFlatPercentDiscount();
                    break;
                case "update":
                    employee.updateOnStock();
                    break;
            }
        }
    }

    private void choosingItems() {
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
            if (input.equals("summary")) {
                store.printSummary();
                summaryOptions();
                break;
            }
            if (input.equals("search")) {
                System.out.println("Type product name that You searching for");
                employee.searchForItem(input);
            }
            customer.addToCart(getIdByInput(input));
        }
    }

    private void summaryOptions() {
        System.out.println("Summary options: ");
        System.out.println("For printing all your discount type print: ");
        System.out.println("For removing any product type: 'remove'");
        System.out.println("For summary of Your products type: 'sum'");
        System.out.println("For going back to shopping type: 'back'");
        System.out.println("For adding discount type: 'discount'");
        System.out.println("For paying type: 'pay'");
        while (true) {
            String input = UserInput.getInput(scanner);
            if (input.equals("remove")) {
                System.out.println("Type product name: ");
                String productName = UserInput.getInput(scanner);
                System.out.println("Type quantity of product that you want to remove: ");
                int quantity = Integer.parseInt(UserInput.getInput(scanner));
                customer.removeFromCart(productName, quantity);
                System.out.println("Product: " + productName + ", has been removed with quantity: " + quantity);
                store.printSummary();
            }
            if (input.equals("print")) {
                store.printCustomerDiscounts();
            }
            if (input.equals("sum")) {
                store.printSummary();
            }
            if (input.equals("search")) {
                System.out.println("Type product name that You searching for");
                employee.searchForItem(input);
            }
            if (input.equals("discount")) {
                System.out.println("Enter your discount code: ");
                String discountCode = UserInput.getInput(scanner);
                store.applyDiscountForCart(discountCode);
                store.printSummary();
            }
            if (input.equals("back")) {
                choosingItems();
                break;
            }
            if (input.equals("pay")) {
                System.out.println("Paying system");
                summaryOptions();
                break;
            }
        }
    }



    //just for testing
    private void addItemsToStock() {
        System.out.println("Type product name, and price");
        employee.addToStock();
    }

    private void addFlatPercentDiscount() {
        employee.addFlatPercentDiscount();
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
        return format("{0} -> Product: {1}, price {2}",
                i,
                getAllProducts().get(i).getName().getValue(),
                getAllProducts().get(i).getPrice());
    }
    private int getNumber(String userInput) {
        return Integer.parseInt(userInput);
    }

    private String getIdByInput(String userInput) {
        ProductId id = getAllProducts().get(getNumber(userInput)).getProductId();
        return id.toString();
    }
}
