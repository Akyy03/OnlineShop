package services;

import models.BrandModel;
import models.CategoryModel;
import models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    private static List<ProductModel> productsList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;


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
            System.out.println("Enter product's brand: ");
            String brandName = scanner.nextLine();
            System.out.println("Enter product's category: ");
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
}
