package org.example.user_interfaces.text_interface;

import lombok.RequiredArgsConstructor;
import org.example.store.employee.Employee;
import org.example.user.UserInput;
import org.example.user_interface.UserInterface;
import org.example.user_interfaces.modules.ChoosingItems;
import org.example.user_interfaces.modules.StockOperations;

import java.util.Scanner;

@RequiredArgsConstructor
public class TextInterface implements UserInterface {
    private final Employee employee;
    private final ChoosingItems choosingItems;
    private final StockOperations stockOperations;
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        label:
        while (true) {
            printOptions();
            String input = UserInput.getInput(scanner);
            switch (input) {
                case "log":
                    System.out.println("Logging system is currently unapproachable, sorry for trouble");
                    System.out.println("""


                            """);
                    continue;
                case "register":
                    System.out.println("Register system is currently unapproachable, sorry for trouble");
                    System.out.println("""


                            """);
                    continue;
                case "shop":
                    choosingItems.option();
                    System.out.println("""


                            """);
                    continue ;
                case "product":
                    stockOperations.option();
                    System.out.println("""


                            """);
                    continue;
                case "discount":
                    addFlatPercentDiscount();
                    System.out.println("""


                            """);
                    continue;
                case "exit":
                    System.out.println("Thank you for visiting, have a nice day");
                    break label;
            }
        }
    }

    private static void printOptions() {
        System.out.println("Welcome in our shop user");
        System.out.println();
        System.out.println("If you want to log-in, type: log");
        System.out.println("If you want to register, type: register");
        System.out.println("Shopping without account, type: shop");
        System.out.println("Changing or creating products, type: product");
        System.out.println("Changing or creating discounts, type: discount");
        System.out.println("If you want to exit, type: exit");
    }

    private void addFlatPercentDiscount() {
        employee.addFlatPercentDiscount();
    }
}
