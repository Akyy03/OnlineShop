package uis;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductsUI {

    // Other needed objects

    public void productsUI() {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        int productsChoice = -1;
        while (productsChoice != 0) {
            System.out.println("Products Menu\n");

            System.out.println("1. Show all products"); // display all products list
            System.out.println("2. Find a product by ID");
            System.out.println("3. Find a product by name");
            System.out.println("4. Find a product by brand");
            System.out.println("5. Filters");
            System.out.println("-----------------------------");
            System.out.println("6. Add a new product");
            System.out.println("7. Update a product");
            System.out.println("8. Remove a product");
            System.out.println("0. Back to main menu");

            try {
                productsChoice = scanner.nextInt();
                scanner.nextLine();

                if (productsChoice == 1) {

                } else if (productsChoice == 2) {

                } else if (productsChoice == 3) {

                } else if (productsChoice == 4) {

                } else if (productsChoice == 5) {

                } else if (productsChoice == 6) {

                } else if (productsChoice == 7) {

                } else if (productsChoice == 8) {

                } else if (productsChoice == 0) {
                    menu.mainMenu();
                }

                if (productsChoice < 0 || productsChoice > 8) {
                    System.out.println("Please use a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option");
            } finally {
                productsUI();
            }
        }
    }
}
