package org.example.user_interfaces.text_interface;

import org.example.account.UserId;
import org.example.product.manager.ProductManager;
import org.example.user.UserInput;
import org.example.user_interfaces.text_interface.modules.ChoosingItems;
import org.example.user_interfaces.text_interface.modules.DiscountOperations;
import org.example.user_interfaces.text_interface.modules.StockOperations;
import org.example.user_interfaces.text_interface.user.UserInterface;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;


@SuppressWarnings("IfCanBeSwitch")
@Component
public class TextInterface implements UserInterface {
    private final ChoosingItems choosingItems;
    private final StockOperations stockOperations;
    private final Scanner scanner = new Scanner(in);
    private final ProductManager productManager;
    private final DiscountOperations discountOperations;
    private final Map<String, Runnable> registeredCommands;

    public TextInterface(ChoosingItems choosingItems, StockOperations stockOperations, ProductManager productManager, DiscountOperations discountOperations) {
        this.choosingItems = choosingItems;
        this.stockOperations = stockOperations;
        this.productManager = productManager;
        this.discountOperations = discountOperations;
        this.registeredCommands = ofEntries(
                entry("log", () -> out.println("Logging system is currently unapproachable, sorry for trouble")),
                entry("register", () -> out.println("Register system is currently unapproachable, sorry for trouble")),
                entry("shop", () -> {
                    UserId.createId();
                    this.choosingItems.option();
                }),
                entry("product", this.stockOperations::option),
                entry("discount", this.discountOperations::options),
                entry("help", TextInterface::printOptions)
        );
    }

    public void run() {
        productManager.refreshStock();
        while (true) {
            out.println("Type help for help");
            String command = UserInput.getInput("store", scanner);
            if (registeredCommands.containsKey(command)) {
                Runnable runnable = registeredCommands.get(command);
                runnable.run();
            } else if (command.equals("exit")) {
                out.println("Thank you for visiting, have a nice day");
                break;

            } else {
                out.println("There is no such command");
            }
            out.println();
        }
    }

    private static void printOptions() {
        out.println("Welcome in our shop user");

        out.println();
        out.println("\t\u001B[1mlog\u001B[0m      -> Log-in user");
        out.println("\t\u001B[1mregister\u001B[0m -> register new user");
        out.println("\t\u001B[1mshop\u001B[0m     -> shopping");
        out.println("\t\u001B[1mproduct\u001B[0m  -> stock management");
        out.println("\t\u001B[1mdiscount\u001B[0m -> discount management");
        out.println("\t\u001B[1mexit\u001B[0m     -> exit");
    }
}
