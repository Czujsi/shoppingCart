package org.example.user_interfaces.modules;

import lombok.RequiredArgsConstructor;
import org.example.user_interfaces.modules.employee.Employee;
import org.example.user.UserInput;

import java.util.Scanner;

import static java.lang.System.out;

@SuppressWarnings("IfCanBeSwitch")
@RequiredArgsConstructor
public class StockOperations {
    private final Employee employee;
    private final StockOutput stockOutput;
    private final Scanner scanner = new Scanner(System.in);

    public void option() {
        while (true) {
            out.println("\nType help for help");
            String input = UserInput.getInput("Stock operations", scanner);
            if (input.equals("back")) {
                break;
            } else if (input.equals("add")) {
                employee.addToStock();
            } else if (input.equals("delete")) {
                employee.removeFromStock(input);
            } else if (input.equals("update")) {
                employee.updateOnStock();
            } else if (input.equals("search")) {
                out.println("Type product name that You are searching for");
                employee.searchForItem(input);
            } else if (input.equals("help")) {
                printingOptions();
            } else if (input.equals("list")) {
                stockOutput.printStock();
            }
            else {
                out.println("Wrong command, try again");
            }
            out.println();
        }
    }

    private static void printingOptions() {
        out.println("Adding new item to stock, type: add");
        out.println("Deleting item from stock, type: delete");
        out.println("Searching for item on stock, type: search");
        out.println("Updating item on stock, type: update");
        out.println("For going back, type: back");
    }
}
