package uis;

import services.ProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductsUI {

    // Other needed objects
    ProductService productService = new ProductService();

    public void productsUI() {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        int productsChoice = -1;
        while (productsChoice != 0) {
            System.out.println("Products Menu\n");

            System.out.println("1. Browse all products"); // display all products list
            System.out.println("2. Browse all categories");
            System.out.println("3. Find a product by ID");
            System.out.println("4. Find a product by brand name");
            System.out.println("5. Filters");
            System.out.println("-----------------------------");
            System.out.println("6. Products management");
            System.out.println("7. Categories management");
            System.out.println("0. Back to main menu");

            try {
                productsChoice = scanner.nextInt();
                scanner.nextLine();

                if (productsChoice == 1) {
                    productService.showProducts();
                } else if (productsChoice == 2) {

                } else if (productsChoice == 3) {
                    productService.findProductByID();
                } else if (productsChoice == 4) {

                } else if (productsChoice == 5) {

                } else if (productsChoice == 6) {
                    productsManagement();
                } else if (productsChoice == 7) {
                    categoriesManagement();
                } else if (productsChoice == 0) {
                    menu.mainMenu();
                }

                if (productsChoice < 0 || productsChoice > 7) {
                    System.out.println("Please use a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option");
            } finally {
                productsUI();
            }
        }
    }

    public void productsManagement() {
        Scanner scanner = new Scanner(System.in);

        int productsManagementChoice = -1;
        while (productsManagementChoice != 0) {
            System.out.println("Products Manager\n");

            System.out.println("1. Add a new product");
            System.out.println("2. Update product");
            System.out.println("3. Remove product");
            System.out.println("0. Back");

            try {
                productsManagementChoice = scanner.nextInt();
                scanner.nextLine();

                if (productsManagementChoice == 1) {
                    productService.addProduct();
                } else if (productsManagementChoice == 2) {

                } else if (productsManagementChoice == 3) {

                } else if (productsManagementChoice == 0) {
                    productsUI();
                }

                if (productsManagementChoice < 0 || productsManagementChoice > 3) {
                    System.out.println("Please use a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option.");
            } finally {
                productsManagement();
            }
        }
    }

    public void categoriesManagement() {
        Scanner scanner = new Scanner(System.in);

        int categoriesChoice = -1;
        while (categoriesChoice != 0) {
            System.out.println("Categories Manager\n");

            System.out.println("1. Add category");
            System.out.println("2. Update category");
            System.out.println("3. Remove category");
            System.out.println("0. Back");

            try {
                categoriesChoice = scanner.nextInt();
                scanner.nextLine();

                if (categoriesChoice == 1) {

                } else if (categoriesChoice == 2) {

                } else if (categoriesChoice == 3) {

                } else if (categoriesChoice == 0) {
                    productsUI();
                }

                if (categoriesChoice < 0 || categoriesChoice > 3) {
                    System.out.println("Please use a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option.");
            } finally {
                categoriesManagement();
            }
        }
    }
}
