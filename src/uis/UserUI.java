package uis;

import models.UserModel;
import services.ProductService;
import services.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserUI {

    UserService userService = new UserService();
    ProductService productService = new ProductService();

    public void userUI() {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        int userChoice = -1;
        while (userChoice != 0) {
            System.out.println("User Menu\n");

            System.out.println("1. Create user account");
            System.out.println("2. Update account information");
            System.out.println("3. Delete user");
            System.out.println("-----------------------------");
            System.out.println("4. Show all users"); // normally, Admin-only panel
            System.out.println("0. Back to main menu");

            try {
                userChoice = scanner.nextInt();

                switch (userChoice) {
//                    case 1 -> userService.createUser();
//
//                    case 2 -> userService.updateUser();
//
//                    case 3 -> userService.removeUser();
//
//                    case 4 -> userService.showUsers();
//
//                    case 0 -> menu.mainMenu();

                    default -> System.out.println("Please use a valid option (0 - 4).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

//    public void cart() {
//        Scanner scanner = new Scanner(System.in);
//        Menu menu = new Menu();
//
//        System.out.println("Enter user ID to view/edit cart:");
//        try {
//            int userId = scanner.nextInt();
//            scanner.nextLine();
//            UserModel user = userService.findUserById(userId);
//
//            if (user == null) {
//                System.out.println("User not found!");
//                return;
//            }
//            int cartChoice = -1;
//            while (cartChoice != 0) {
//                System.out.println("Your Cart\n");
//
//                System.out.println("1. Add product to cart");
//                System.out.println("2. Remove product from cart");
//                System.out.println("3. Show cart");
//                System.out.println("4. Checkout");
//                System.out.println("0. Back");
//
//
//                cartChoice = scanner.nextInt();
//
//                switch (cartChoice) {
////                    case 1 -> productService.addToCart(user);
////
////                    case 2 -> productService.removeProductFromCart(user);
////
////                    case 3 -> productService.showCart(user);
////
////                    case 4 -> productService.checkout(user);
////
////                    case 0 -> menu.mainMenu();
//
//                    default -> System.out.println("Please use a valid option (0 - 4).");
//                }
//
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("Please use a valid option. Input should be a number.");
//            scanner.nextLine();
//        }
//    }
}
