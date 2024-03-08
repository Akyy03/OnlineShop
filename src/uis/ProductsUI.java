package uis;

import services.CategoryService;
import services.ProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductsUI {

    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();

    public void productsUI() {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        int productsChoice = -1;
        while (productsChoice != 0) {
            System.out.println("Products Menu\n");

            System.out.println("1. Browse all products"); // display all products list
            System.out.println("2. Browse all categories"); // display all categories lists
            System.out.println("3. Filters"); // find by id, name, brand, price etc
            System.out.println("-----------------------------");
            System.out.println("4. Products management"); // products CRUD operations
            System.out.println("5. Categories management"); // categories CRUD operations
            System.out.println("0. Back to main menu");

            try {
                productsChoice = scanner.nextInt();
                scanner.nextLine();

                switch (productsChoice) {
                    case 1 -> productService.showProducts();

                    case 2 -> categoryService.showCategories();

                    case 3 -> filters();

                    case 4 -> productsManagement();

                    case 5 -> categoriesManagement();

                    case 0 -> menu.mainMenu();

                    default -> System.out.println("Please use a valid option (0 - 5).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
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

                switch (productsManagementChoice) {
                    case 1 -> productService.addProduct();

                    case 2 -> productService.updateProduct();

                    case 3 -> productService.removeProduct();

                    case 0 -> productsUI();

                    default -> System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

    public void categoriesManagement() {
        Scanner scanner = new Scanner(System.in);

        int categoriesChoice = -1;
        while (categoriesChoice != 0) {
            System.out.println("Categories Manager\n");

            System.out.println("1. Add a category");
            System.out.println("2. Update category");
            System.out.println("3. Remove category");
            System.out.println("0. Back");

            try {
                categoriesChoice = scanner.nextInt();
                scanner.nextLine();

                switch (categoriesChoice) {
                    case 1 -> categoryService.addCategory();

                    case 2 -> categoryService.updateCategory();

                    case 3 -> categoryService.removeCategory();

                    case 0 -> productsUI();

                    default -> System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

    public void filters() {
        Scanner scanner = new Scanner(System.in);

        int filtersChoice = -1;
        while (filtersChoice != 0) {
            System.out.println("Filters\n");

            System.out.println("1. Find product by ID");
            System.out.println("2. Find product by product name");
            System.out.println("3. Find product by brand name");
            System.out.println("4. Price filters");
            System.out.println("0. Back");
            try {
                filtersChoice = scanner.nextInt();

                switch (filtersChoice) {
                    case 1 -> productService.findProductById();

                    case 2 -> productService.findProductByName();

                    case 3 -> productService.findProductByBrandName();

                    case 4 -> priceFilters();

                    case 0 -> productsUI();

                    default -> System.out.println("Please use a valid option (0 - 4).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

    public void priceFilters() {
        Scanner scanner = new Scanner(System.in);

        int priceFiltersChoice = -1;
        while (priceFiltersChoice != 0) {
            System.out.println("Find products by price: \n");

            System.out.println("1. Cheaper than");
            System.out.println("2. More expensive than");
            System.out.println("3. Custom price range");
            System.out.println("0. Back");

            try {
                priceFiltersChoice = scanner.nextInt();

                switch (priceFiltersChoice) {
                    case 1 -> productService.cheaperThan();

                    case 2 -> productService.moreExpensiveThan();

                    case 3 -> productService.productsInPriceRange();

                    case 0 -> filters();

                    default -> System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }
}
