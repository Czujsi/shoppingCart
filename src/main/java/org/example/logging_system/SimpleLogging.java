package org.example.logging_system;

import org.example.text_interface.EmployeeTextInterface;
import org.example.text_interface.Shop;

import java.util.Scanner;

public class SimpleLogging {
    Scanner scanner = new Scanner(System.in);
    public void logIn() {
        logging();
    }

    private void logging() {
        System.out.println("For going to shop options type: exit");
        System.out.println("Please enter your login");

        while (true) {
            scanner.reset();
            String login = scanner.nextLine();
            if (login.equals("exit")) {
                Shop shop = new Shop();
                shop.run();
                break;
            }
            String password = scanner.nextLine();
            if (login.equals("employee") && password.equals("1234")) {
                scanner.reset();
                EmployeeTextInterface employeeTextInterface;
                employeeTextInterface.welcomeEmployee();
                break;
            } else {
                System.out.println("Wrong login or password");
                scanner.reset();
            }
        }
    }
}
