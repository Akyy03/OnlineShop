package uis;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    ProductsUI productsUI = new ProductsUI();
    UserUI userUI = new UserUI();

    public void mainMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {
            System.out.println("Welcome to your online store!\n");

            System.out.println("1. Browse products menu"); // display list and sort options
            System.out.println("2. Your account"); // user personal information
            System.out.println("3. Your cart"); // user shopping cart + checkout
            System.out.println("0. Close");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> productsUI.productsUI();

                    case 2 -> userUI.userUI();

                    case 3 -> userUI.cart();

                    case 0 -> System.exit(0);

                    default -> System.out.println("Please use a valid option (0 - 3).");
                }
            } else {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.next();
            }
        }
    }
}