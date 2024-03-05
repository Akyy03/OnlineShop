package services;

import models.*;
import uis.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    private static List<UserModel> usersList = new ArrayList<>();
    private static List<ProductModel> productsList = new ArrayList<>();
    private static int idCounter = 1;
    Scanner scanner = new Scanner(System.in);

    public void showProducts() {
        if (productsList.isEmpty()) {
            System.out.println("No products on stock.\n");
        } else {
            System.out.println("All our products: ");
            for (ProductModel product : productsList) {
                System.out.println("Product ID: " + product.getId() + " | Product Name: " + product.getProductName());
                System.out.println("Price: " + product.getPrice() + " | Available Quantity: " + product.getQuantity());
                System.out.println("Max Quantity: " + product.getMaxQuantity());

                BrandModel brand = product.getBrand();
                if (!brand.getBrandName().isEmpty()) {
                    System.out.println("Brand: " + brand.getBrandName());
                } else {
                    System.out.println("No brand specified");
                }

                CategoryModel category = product.getCategory();
                if (!category.getCategoryName().isEmpty()) {
                    System.out.println("Category: " + category.getCategoryName() + "\n");
                } else {
                    System.out.println("No category specified\n");
                }
            }
        }
    }

    public void addProduct() {

        while (true) {
            ProductModel productModel = new ProductModel();
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
            System.out.println("Enter product max quantity: ");
            int productMaxQuantity = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter product's brand (or leave blank if not specified): ");
            String brandName = scanner.nextLine();
            System.out.println("Enter product's category (or leave blank if not specified): ");
            String categoryName = scanner.nextLine();

            productModel.setId(idCounter++);
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

            productsList.add(productModel);

            System.out.println("Product added successfully!\n");
        }
    }

    public void updateProduct() {
        System.out.println("Enter the ID of the product that you wish to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        ProductModel product = null;
        for (ProductModel product2 : productsList) {
            if (product2.getId() == productId) {
                product = product2;
                break;
            }
        }

        if (product == null) {
            System.out.println("Product with ID " + productId + " not found.");
            return;
        }

        System.out.println("Enter a new product name (or leave blank to keep current): ");
        String newProductName = scanner.nextLine();
        if (!newProductName.isEmpty()) {
            product.setProductName(newProductName);
        }

        System.out.println("Enter a new price for the product (or leave blank to keep current): ");
        String newPrice = scanner.nextLine();
        if (!newPrice.isEmpty()) {
            product.setPrice(Float.parseFloat(newPrice));
        }

        System.out.println("Enter a new quantity for the product (or leave blank to keep current): ");
        String newQuantity = scanner.nextLine();
        if (!newQuantity.isEmpty()) {
            product.setQuantity(Integer.parseInt(newQuantity));
        }

        System.out.println("Enter a new max quantity for the product (or leave blank to keep current): ");
        String newMaxQuantity = scanner.nextLine();
        if (!newMaxQuantity.isEmpty()) {
            product.setMaxQuantity(Integer.parseInt(newMaxQuantity));
        }

        System.out.println("Enter a new brand name for the product (or leave blank to keep current): ");
        String newBrandName = scanner.nextLine();
        if (!newBrandName.isEmpty()) {
            BrandModel brand = new BrandModel();
            brand.setBrandName(newBrandName);
            product.setBrand(brand);
        }

        System.out.println("Enter a new category name for the product (or leave blank to keep current): ");
        String newCategoryName = scanner.nextLine();
        if (!newCategoryName.isEmpty()) {
            CategoryModel category = new CategoryModel();
            category.setCategoryName(newCategoryName);
            product.setCategory(category);
        }

        System.out.println("Product data updated successfully!\n");
    }

    public void removeProduct() {
        System.out.println("Enter the ID of the product that you wish to remove: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        ProductModel removedProduct = null;
        for (int i = 0; i < productsList.size(); i++) {
            ProductModel product = productsList.get(i);
            if (product.getId() == productId) {
                removedProduct = productsList.remove(i);
                break;
            }
        }

        if (removedProduct == null) {
            System.out.println("Product with ID " + productId + " not found.");
        } else {
            System.out.println("Product removed successfully!\n");
        }
    }

    public void findProductById() {
        System.out.println("Enter the ID of the product that you are looking for: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        for (ProductModel product : productsList) {
            if (product.getId() == productId) {

                System.out.println("Product ID: " + product.getId() + " | Product Name: " + product.getProductName());
                System.out.println("Price: " + product.getPrice() + " | Available Quantity: " + product.getQuantity());
                System.out.println("Max Quantity: " + product.getMaxQuantity());

                BrandModel brand = product.getBrand();
                if (!brand.getBrandName().isEmpty()) {
                    System.out.println("Brand: " + brand.getBrandName());
                } else {
                    System.out.println("No brand specified");
                }

                CategoryModel category = product.getCategory();
                if (!category.getCategoryName().isEmpty()) {
                    System.out.println("Category: " + category.getCategoryName() + "\n");
                } else {
                    System.out.println("No category specified\n");
                }

                found = true;
                break;

            }
        }
        if (!found) {
            System.out.println("Product with ID " + productId + " not found.");
        }

    }


    public void findProductByName() {
        System.out.println("Enter the name of the product that you are looking for: ");
        String productName = scanner.nextLine();

        List<ProductModel> matchingProducts = new ArrayList<>();

        for (ProductModel product : productsList) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                matchingProducts.add(product);
            }
        }

        if (!matchingProducts.isEmpty()) {
            System.out.println("Matching products:");
            for (ProductModel product : matchingProducts) {
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
        } else {
            System.out.println("No results found for product name containing: " + productName);
        }
    }


    public void findProductByBrandName() {
        System.out.println("Enter the name of the brand that you are looking for: ");
        String brandName = scanner.nextLine();

        List<ProductModel> matchingBrands = new ArrayList<>();

        for (ProductModel product : productsList) {
            BrandModel brand = product.getBrand();
            if (brand.getBrandName().toLowerCase().contains(brandName.toLowerCase())) {
                matchingBrands.add(product);
            }
        }

        if (!matchingBrands.isEmpty()) {
            System.out.println("Matching products:");
            for (ProductModel product : matchingBrands) {
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
        } else {
            System.out.println("No results found for brand name containing: " + brandName);
        }
    }

    public void cheaperThan() {
        System.out.println("Enter the maximum price for products you want to see: ");
        float maxPrice = scanner.nextFloat();
        scanner.nextLine();

        List<ProductModel> productsCheaperThan = new ArrayList<>();

        for (ProductModel product : productsList) {
            if (product.getPrice() <= maxPrice) {
                productsCheaperThan.add(product);
            }
        }

        if (productsCheaperThan.isEmpty()) {
            System.out.println("No products on stock within the price range.\n");
        } else {
            System.out.println("All our products within your price range: ");
            for (ProductModel product : productsCheaperThan) {
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

    public void moreExpensiveThan() {
        System.out.println("Enter the minimum price for products you want to see: ");
        float minPrice = scanner.nextFloat();
        scanner.nextLine();

        List<ProductModel> productsMoreExpensiveThan = new ArrayList<>();

        for (ProductModel product : productsList) {
            if (product.getPrice() > minPrice) {
                productsMoreExpensiveThan.add(product);
            }
        }

        if (productsMoreExpensiveThan.isEmpty()) {
            System.out.println("No products on stock within the price range.\n");
        } else {
            System.out.println("All our products within your price range: ");
            for (ProductModel product : productsMoreExpensiveThan) {
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

    public void productsInPriceRange() {
        System.out.println("Enter the minimum price for products you want to see: ");
        float minPrice = scanner.nextFloat();

        System.out.println("Enter the maximum price for products you want to see: ");
        float maxPrice = scanner.nextFloat();
        scanner.nextLine();

        List<ProductModel> filteredProducts = new ArrayList<>();

        for (ProductModel product : productsList) {
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

    public void addToCart(UserModel user) {
        System.out.println("Enter the product ID to add to your cart:");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        ProductModel productToAdd = null;
        for (ProductModel product : productsList) {
            if (product.getId() == productId) {
                productToAdd = product;
                break;
            }
        }

        if (productToAdd != null) {
            float totalPrice = productToAdd.getPrice() * quantity;
            if (user.getBalance() >= totalPrice) {
                if (user.addToCart(productToAdd, quantity)) {
                    System.out.println("Product added to cart successfully!");
                } else {
                    System.out.println("Insufficient stock! Cannot add this quantity to the cart.");
                }
            } else {
                System.out.println("Insufficient balance! Cannot add items to the cart.");
            }
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }


    public void showCart(UserModel user) {
        if (user.getShoppingCart().isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Shopping cart for user " + user.getName() + ":");
            for (ProductModel product : user.getShoppingCart()) {
                float totalPrice = product.getPrice() * product.getCartQuantity();
                System.out.println("Product ID: " + product.getId() + " | Product Name: " + product.getProductName());
                System.out.println("Total Price: " + totalPrice + " | Quantity: " + product.getCartQuantity());
            }
        }
    }

    public void removeProductFromCart(UserModel user) {
        System.out.println("Enter the ID of the product you wish to remove from your cart:");
        int productId = scanner.nextInt();

        user.removeFromCart(productId);
    }

    public void checkout(UserModel user) {
        Menu menu = new Menu();
        if (user.getShoppingCart().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Reviewing order for user " + user.getName() + ":");
        for (ProductModel product : user.getShoppingCart()) {
            float totalPrice = product.getPrice() * product.getCartQuantity();
            System.out.println("Product ID: " + product.getId() + " | Product Name: " + product.getProductName());
            System.out.println("Total Price: " + totalPrice + " | Quantity: " + product.getCartQuantity());

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nProceeding to checkout ...\n");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Is this your address?: \n");

        AddressModel address = user.getAddress();
        if (!address.getCountry().isEmpty()) {
            System.out.println("Country: " + address.getCountry());
        } else {
            System.out.println("No country specified");
        }
        if (!address.getCity().isEmpty()) {
            System.out.println("City: " + address.getCity());
        } else {
            System.out.println("No city specified");
        }
        if (!address.getStreet().isEmpty()) {
            System.out.println("Street: " + address.getStreet());
        } else {
            System.out.println("No street specified");
        }
        Integer zipcode = address.getZipcode();
        if (zipcode != null && zipcode != 0) {
            System.out.println("Zip code: " + address.getZipcode());
        } else {
            System.out.println("No zip code specified");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Use '1' for YES");
        System.out.println("Use '2' for NO and proceed to add shipping address");

        int shippingChoice = scanner.nextInt();
        scanner.nextLine();

        if (shippingChoice == 1) {
            System.out.println("Using existing address as the shipping address.");
        } else if (shippingChoice == 2) {
            System.out.println("Enter the new shipping address details:");

            System.out.println("Enter the country:");
            String country = scanner.nextLine();

            System.out.println("Enter the city:");
            String city = scanner.nextLine();

            System.out.println("Enter the street:");
            String street = scanner.nextLine();

            System.out.println("Enter the zip code:");
            int zipCode = scanner.nextInt();
            scanner.nextLine();

            AddressModel shippingAddress = new AddressModel(country, city, street, zipCode);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nOrder details: \n");

        float totalSum = 0;
        for (ProductModel product : user.getShoppingCart()) {
            totalSum += product.getPrice() * product.getCartQuantity();
        }

        float newBalance = user.getBalance() - totalSum;

        user.setBalance(newBalance);

        System.out.println("Total sum of items in your cart: " + totalSum);
        System.out.println("Your new balance after the order: " + newBalance);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nShipping and billing address processed!");

        System.out.println("\nA receipt will be sent shortly via email: ");
        System.out.println(user.getEmail() + "\n");

        System.out.println("Thank you for your order!\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (ProductModel product : user.getShoppingCart()) {
            int initialStock = product.getQuantity();
            int orderedQuantity = product.getCartQuantity();
            int newStock = initialStock - orderedQuantity;
            product.setQuantity(newStock);
            System.out.println("***Admin info: Product stock updated.***\n");

            user.getShoppingCart().clear();
            menu.mainMenu();
        }
    }
}
