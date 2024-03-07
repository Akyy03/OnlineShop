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

                if (productsChoice == 1) {
                    productService.showProducts();
                } else if (productsChoice == 2) {
                    categoryService.showCategories();
                } else if (productsChoice == 3) {
                    filters();
                } else if (productsChoice == 4) {
                    productsManagement();
                } else if (productsChoice == 5) {
                    categoriesManagement();
                } else if (productsChoice == 0) {
                    menu.mainMenu();
                }

                if (productsChoice < 0 || productsChoice > 5) {
                    System.out.println("Please use a valid option (0 - 5).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option");
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
                    productService.updateProduct();
                } else if (productsManagementChoice == 3) {
                    productService.removeProduct();
                } else if (productsManagementChoice == 0) {
                    productsUI();
                }

                if (productsManagementChoice < 0 || productsManagementChoice > 3) {
                    System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
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

                if (categoriesChoice == 1) {
                    categoryService.addCategory();
                } else if (categoriesChoice == 2) {
                    categoryService.updateCategory();
                } else if (categoriesChoice == 3) {
                    categoryService.removeCategory();
                } else if (categoriesChoice == 0) {
                    productsUI();
                }

                if (categoriesChoice < 0 || categoriesChoice > 3) {
                    System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
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
                scanner.nextLine();

                if (filtersChoice == 1) {
                    productService.findProductById();
                } else if (filtersChoice == 2) {
                    productService.findProductByName();
                } else if (filtersChoice == 3) {
                    productService.findProductByBrandName();
                } else if (filtersChoice == 4) {
                    priceFilters();
                } else if (filtersChoice == 0) {
                    productsUI();
                }

                if (filtersChoice < 0 || filtersChoice > 4) {
                    System.out.println("Please use a valid option (0 - 4).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input should be a number.");
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
                scanner.nextLine();

                if (priceFiltersChoice == 1) {
                    productService.cheaperThan();
                } else if (priceFiltersChoice == 2) {
                    productService.moreExpensiveThan();
                } else if (priceFiltersChoice == 3) {
                    productService.productsInPriceRange();
                } else if (priceFiltersChoice == 0) {
                    filters();
                }

                if (priceFiltersChoice < 0 || priceFiltersChoice > 3) {
                    System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input should be a number.");
            }
        }
    }
}
