package uis;

import dao.ProductDao;
import models.BrandModel;
import models.CategoryModel;
import models.ProductModel;
import services.CategoryService;
import services.ProductService;

import java.io.IOException;
import java.util.*;

public class ProductsUI {

    private static int productIdCounter = 1;
    private static int categoryIdCounter = 1;

    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    ProductDao productDao = new ProductDao();

    public void productsUI() throws IOException {
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
                    case 1 -> showProducts();

                    case 2 -> showCategories();

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

    // 1. Browse all products

    private void showProducts() throws IOException {
        productService.showList();
    }

    // 2. Browse all categories

    private void showCategories() throws IOException {
        categoryService.showCategoriesList();
    }

    private void productsManagement() throws IOException {
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
                    case 1 -> addProduct();

                    case 2 -> updateProduct();

                    case 3 -> removeProduct();

                    case 0 -> productsUI();

                    default -> System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

    // 1. Add a new product

    private void addProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ProductModel productModel = new ProductModel();
            productModel.setId(productIdCounter++);
            System.out.println("Enter a product display name (or type 'quit' to cancel): ");
            String productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter product's price: ");
            float productPrice = scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Enter product quantity: ");
            int productQuantity = scanner.nextInt();
            scanner.nextLine();

            int productMaxQuantity;
            while (true) {
                System.out.println("Enter product max quantity (must be equal to or greater than quantity): ");
                productMaxQuantity = scanner.nextInt();
                scanner.nextLine();
                if (productMaxQuantity >= productQuantity) {
                    break;
                } else {
                    System.out.println("Max quantity must be equal to or greater than quantity.");
                }
            }

            System.out.println("Enter product's brand (or leave blank if not specified): ");
            String brandName = scanner.nextLine();
            System.out.println("Enter product's category (or leave blank if not specified): ");
            String categoryName = scanner.nextLine();

            productModel.setProductName(productName);
            productModel.setPrice(productPrice);
            productModel.setQuantity(productQuantity);
            productModel.setMaxQuantity(productMaxQuantity);

            BrandModel brandModel = new BrandModel();
            productModel.setBrand(brandModel);
            brandModel.setBrandName(brandName);

            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCategoryName(categoryName);
            productModel.setCategory(categoryModel);

            productService.addProduct(productModel);

            System.out.println("Product added successfully!\n");
        }
    }

    // 2. Update product

    private void updateProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);

        if (productDao.getList().isEmpty()) {
            System.out.println("No products found.\n");
        } else {

            System.out.println("Enter the ID of the product that you wish to update: ");
            int productId = scanner.nextInt();
            scanner.nextLine();
            ProductModel productModel = productService.getProductModel(productId);
            int choice = -1;
            while (choice != 0) {
                System.out.println("1. Update product name");
                System.out.println("2. Update product price");
                System.out.println("3. Update product quantity / max quantity");
                System.out.println("4. Update product brand");
                System.out.println("5. Update product category");
                System.out.println("0. Back");
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    System.out.println("Enter a new product name (or leave blank to keep current): ");
                    String newProductName = scanner.nextLine();
                    if (!newProductName.isEmpty()) {
                        productModel.setProductName(newProductName);
                        productService.updateProduct(productModel);
                    }
                } else if (choice == 2) {
                    System.out.println("Enter a new price for the product (or leave blank to keep current): ");
                    String newPrice = scanner.nextLine();
                    if (!newPrice.isEmpty()) {
                        try {
                            float price = Float.parseFloat(newPrice);
                            productModel.setPrice(price);
                            productService.updateProduct(productModel);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. It should be a number. Keeping current.");
                        }
                    }
                } else if (choice == 3) {
                    System.out.println("Enter a new quantity for the product (or leave blank to keep current): ");
                    String newQuantity = scanner.nextLine();
                    if (!newQuantity.isEmpty()) {
                        try {
                            int quantity = Integer.parseInt(newQuantity);
                            if (quantity <= productModel.getMaxQuantity()) {
                                productModel.setQuantity(quantity);
                                productService.updateProduct(productModel);
                            } else {
                                System.out.println("Error: Quantity cannot be greater than max quantity. Keeping current.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. It should be a number. Keeping current.");
                        }
                    }

                    System.out.println("Enter a new max quantity for the product (or leave blank to keep current): ");
                    String newMaxQuantity = scanner.nextLine();
                    if (!newMaxQuantity.isEmpty()) {
                        try {
                            int maxQuantity = Integer.parseInt(newMaxQuantity);
                            if (maxQuantity >= productModel.getQuantity()) {
                                productModel.setMaxQuantity(maxQuantity);
                                productService.updateProduct(productModel);
                            } else {
                                System.out.println("Error: Max quantity cannot be less than quantity. Keeping current.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. It should be a number. Keeping current.");
                        }
                    }
                } else if (choice == 4) {
                    System.out.println("Enter a new brand name for the product (or leave blank to keep current): ");
                    String newBrandName = scanner.nextLine();
                    if (!newBrandName.isEmpty()) {
                        BrandModel brand = new BrandModel();
                        brand.setBrandName(newBrandName);
                        productModel.setBrand(brand);
                        productService.updateProduct(productModel);
                    }
                } else if (choice == 5) {
                    System.out.println("Enter a new category name for the product (or leave blank to keep current): ");
                    String newCategoryName = scanner.nextLine();
                    if (!newCategoryName.isEmpty()) {
                        CategoryModel category = new CategoryModel();
                        category.setCategoryName(newCategoryName);
                        productModel.setCategory(category);
                        productService.updateProduct(productModel);
                    }
                } else if (choice == 0) {
                    productsManagement();
                }
                System.out.println("Product data updated successfully!\n");
            }
        }
    }

    // 3. Remove product

    private void removeProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (productDao.getList().isEmpty()) {
            System.out.println("No products found.\n");
        } else {
            showProducts();
            System.out.println("Choose a product to remove: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            productService.removeProduct(id);
            System.out.println("Product removed successfully!\n");
        }
    }

    private void categoriesManagement() throws IOException {
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
                    case 1 -> addCategory();

                    case 2 -> updateCategory();

                    case 3 -> removeCategory();

                    case 0 -> productsUI();

                    default -> System.out.println("Please use a valid option (0 - 3).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

    // 1. Add a category

    private void addCategory() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(categoryIdCounter++);
            System.out.println("Enter a category name (or type 'quit' to cancel): ");
            String categoryName = scanner.nextLine();
            if (categoryName.equalsIgnoreCase("quit")) {
                break;
            }

            categoryModel.setCategoryName(categoryName);
            categoryService.addCategory(categoryModel);
            System.out.println("Category added successfully!\n");
        }
    }

    // 2. Update category

    private void updateCategory() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the category that you wish to update: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        CategoryModel categoryModel = categoryService.getCategoryModel(categoryId);
        System.out.println("Enter a new category name (or leave blank to keep current): ");
        String newCategoryName = scanner.nextLine();
        if (!newCategoryName.isEmpty()) {
            categoryService.updateCategory(categoryModel);
        }
    }

    // 3. Remove category

    private void removeCategory() throws IOException {
        Scanner scanner = new Scanner(System.in);
        showCategories();
        System.out.println("Choose a category to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        categoryService.removeCategory(id);
        System.out.println("Category removed successfully!\n");
    }

    private void filters() throws IOException {
        Scanner scanner = new Scanner(System.in);

        int filtersChoice = -1;
        while (filtersChoice != 0) {
            System.out.println("Filters\n");

            System.out.println("1. Find product by ID");
            System.out.println("2. Price filters");
            System.out.println("0. Back");
            try {
                filtersChoice = scanner.nextInt();

                switch (filtersChoice) {
                    case 1 -> findProductByID();

                    case 2 -> priceFilters();

                    case 0 -> productsUI();

                    default -> System.out.println("Please use a valid option (0 - 2).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

    // 1. Find product by ID

    private void findProductByID() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the product that you are looking for: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        try {
            ProductModel productModel = productService.getProductModel(productId);

            if (productModel != null) {
                System.out.println("Product found:");
                System.out.println("Product ID: " + productModel.getId() + " | Product Name: " + productModel.getProductName());
                System.out.println("Price: " + productModel.getPrice() + " | Available Quantity: " + productModel.getQuantity());
                System.out.println("Max Quantity: " + productModel.getMaxQuantity());

                BrandModel brand = productModel.getBrand();
                if (!brand.getBrandName().isEmpty()) {
                    System.out.println("Brand: " + brand.getBrandName());
                } else {
                    System.out.println("No brand specified");
                }

                CategoryModel category = productModel.getCategory();
                if (!category.getCategoryName().isEmpty()) {
                    System.out.println("Category: " + category.getCategoryName() + "\n");
                } else {
                    System.out.println("No category specified\n");
                }
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    private void priceFilters() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the minimum price for products you want to see: ");
        float minPrice = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("Enter the maximum price for products you want to see: ");
        float maxPrice = scanner.nextFloat();
        scanner.nextLine();

        List<ProductModel> filteredProducts = new ArrayList<>();

        for (ProductModel product : productDao.getList()) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredProducts.add(product);
            }
        }

        if (filteredProducts.isEmpty()) {
            System.out.println("No products found within the specified price range.\n");
        } else {
            System.out.println("All our products within your specified price range: ");
            for (ProductModel product : filteredProducts) {
                System.out.println("Product ID: " + product.getId() + " | Product Name: " + product.getProductName());
                System.out.println("Price: " + product.getPrice() + " | Available Quantity: " + product.getQuantity());
                System.out.println("Max Quantity: " + product.getMaxQuantity());

                BrandModel brand = product.getBrand();
                if (brand != null && !brand.getBrandName().isEmpty()) {
                    System.out.println("Brand: " + brand.getBrandName());
                } else {
                    System.out.println("No brand specified");
                }

                CategoryModel category = product.getCategory();
                if (category != null && !category.getCategoryName().isEmpty()) {
                    System.out.println("Category: " + category.getCategoryName() + "\n");
                } else {
                    System.out.println("No category specified\n");
                }
            }
        }
    }
}
