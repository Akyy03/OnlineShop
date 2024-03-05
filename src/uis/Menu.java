package uis;

import models.UserModel;
import services.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {


    ProductsUI productsUI = new ProductsUI();
    UserUI userUI = new UserUI();
    UserService userService = new UserService();
    UserModel user = new UserModel();

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {
            System.out.println("Welcome to your online store!\n");

            System.out.println("1. Browse products menu"); // display list and sort options
            System.out.println("2. Your account"); // user personal informations
            System.out.println("3. Your cart"); // user shopping cart + checkout
            System.out.println("0. Close");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    productsUI.productsUI();
                } else if (choice == 2) {
                    userUI.userUI();
                } else if (choice == 3) {
                    userUI.cart();
                } else if (choice == 0) {
                    System.exit(0);
                } else {
                    System.out.println("Please use a valid option.");
                }
            } else {
                System.out.println("Please use a valid option.");
                scanner.next();
            }
        }
    }
}