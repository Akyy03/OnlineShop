package services;

import models.CategoryModel;
import models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    private List productsList = new ArrayList();
    ProductModel productModel = new ProductModel();
    Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public void showProducts(){
        if(productsList.isEmpty()){
            System.out.println("No products on stock.");
        } else {
            // print products list
        }
    }

    public void findProductByID(){

    }

    public void addProduct(){
        while (true){
            System.out.println("Enter a product display name (or type 'quit' to cancel): ");
            String productName = scanner.nextLine();
            if(productName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter product's price: ");
            int productPrice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter product quantity: ");
            int productQuantity = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter product max quantity: ");
            int productMaxQuantity = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter product's brand: ");
            String productBrand = scanner.nextLine();
            System.out.println("Enter product's category: ");
            String productCategory = scanner.nextLine();

            CategoryModel categoryModel = new CategoryModel();

            productModel.setId(idCounter++);
            productModel.setProductName(productName);
            productModel.setPrice(productPrice);
            productModel.setQuantity(productQuantity);
            productModel.setMaxQuantity(productMaxQuantity);
            productModel.setBrand(productBrand);
            productModel.setCategory(categoryModel);
            productsList.add(productModel.getId(), productModel);
        }
    }
}
