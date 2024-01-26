package org.example.user_interfaces.text_interface;

import lombok.RequiredArgsConstructor;
import org.example.store.employee.Employee;
import org.example.user.UserInput;
import org.example.user_interface.UserInterface;

import java.util.Scanner;

@RequiredArgsConstructor
public class TextInterface implements UserInterface {
    private final Employee employee;
    private final ChoosingItems choosingItems;
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
                    choosingItems.option();
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

    //just for testing
    private void addItemsToStock() {
        System.out.println("Type product name, and price");
        employee.addToStock();
    }

    private void addFlatPercentDiscount() {
        employee.addFlatPercentDiscount();
    }

}
