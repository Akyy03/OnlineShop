package uis;

import dao.ProductDao;
import dao.UserDao;
import models.AddressModel;
import models.ProductModel;
import models.UserModel;
import services.UserService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserUI {

    private static int idCounter = 1;
    UserService userService = new UserService();
    UserDao userDao = new UserDao();
    ProductDao productDao = new ProductDao();


    public void userUI() throws IOException {
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
                    case 1 -> createUser();

                    case 2 -> updateUser();

                    case 3 -> removeUser();

                    case 4 -> showUsers();

                    case 0 -> menu.mainMenu();

                    default -> System.out.println("Please use a valid option (0 - 4).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option. Input should be a number.");
                scanner.nextLine();
            }
        }
    }

    // Show users

    private void showUsers() throws IOException {
        userService.showList();
    }

    // Create User

    private void createUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            UserModel userModel = new UserModel();
            userModel.setId(idCounter++);
            System.out.println("Enter user's name (or type 'quit' to cancel): ");
            String userName = scanner.nextLine();
            if (userName.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("Enter user's email address: ");
            String userEmail = scanner.nextLine();
            System.out.println("Enter user's balance: ");
            float userBalance = scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Enter user's country (or leave blank if not specified): ");
            String country = scanner.nextLine();
            System.out.println("Enter user's city (or leave blank if not specified): ");
            String city = scanner.nextLine();
            System.out.println("Enter user's street (or leave blank if not specified): ");
            String street = scanner.nextLine();
            System.out.println("Enter user's zip code (or leave blank if not specified):");
            String zipcodeInput = scanner.nextLine();

            userModel.setName(userName);
            userModel.setEmail(userEmail);
            userModel.setBalance(userBalance);

            AddressModel addressModel = new AddressModel();
            userModel.setAddress(addressModel);
            addressModel.setCountry(country);
            addressModel.setCity(city);
            addressModel.setStreet(street);

            if (!zipcodeInput.isEmpty()) {
                try {
                    int zipcode = Integer.parseInt(zipcodeInput);
                    addressModel.setZipcode(zipcode);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid zip code format. Leaving it blank.");
                }
            }

            userService.addUser(userModel);
            System.out.println("User added successfully!\n");
        }
    }

    // Update user information

    private void updateUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the account you wish to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        UserModel userModel = userService.getUserModel(userId);
        int choice = -1;
        while (choice != 0) {

            System.out.println("1. Update user name");
            System.out.println("2. Update user email");
            System.out.println("3. Update user balance");
            System.out.println("4. Update user address");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.println("Enter a new user name (or leave blank to keep current): ");
                String newUserName = scanner.nextLine();
                if (!newUserName.isEmpty()) {
                    userModel.setName(newUserName);
                }
            } else if (choice == 2) {
                System.out.println("Enter a new user email address (or leave blank to keep current): ");
                String newUserEmail = scanner.nextLine();
                if (!newUserEmail.isEmpty()) {
                    userModel.setEmail(newUserEmail);
                }
            } else if (choice == 3) {
                System.out.println("Enter a new balance for the user (or leave blank to keep current): ");
                String newBalance = scanner.nextLine();
                if (!newBalance.isEmpty()) {
                    try {
                        float balance = Float.parseFloat(newBalance);
                        userModel.setBalance(balance);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input. It should be a number. Keeping current.");
                    }
                }
            } else if (choice == 4) {
                AddressModel address = userModel.getAddress();
                System.out.println("Enter a new country for the user (or leave blank to keep current): ");
                String newCountry = scanner.nextLine();
                if (!newCountry.isEmpty()) {
                    address.setCountry(newCountry);
                }

                System.out.println("Enter a new city for the user (or leave blank to keep current): ");
                String newCity = scanner.nextLine();
                if (!newCity.isEmpty()) {
                    address.setCity(newCity);
                }

                System.out.println("Enter a new street for the user (or leave blank to keep current): ");
                String newStreet = scanner.nextLine();
                if (!newStreet.isEmpty()) {
                    address.setStreet(newStreet);
                }

                System.out.println("Enter a new zipcode for the user (or leave blank to keep current): ");
                String newZipCode = scanner.nextLine();
                if (!newZipCode.isEmpty()) {
                    try {
                        int zipcode = Integer.parseInt(newZipCode);
                        address.setZipcode(zipcode);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input. It should be a number. Keeping current.");
                    }
                }
            }

            System.out.println("User updated successfully!\n");
        }
    }

    // Remove user

    private void removeUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        showUsers();
        System.out.println("Choose an user to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        userService.removeUser(id);
        System.out.println("User removed successfully!\n");
    }

    // Cart

    public UserModel findUserById(int id) throws IOException {
        for (UserModel user : userDao.getList()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void cart() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        System.out.println("Enter user ID to view/edit cart:");
        try {
            int userId = scanner.nextInt();
            scanner.nextLine();
            UserModel user = findUserById(userId);

            if (user == null) {
                System.out.println("User not found!");
                return;
            }
            int cartChoice = -1;
            while (cartChoice != 0) {
                System.out.println("Your Cart\n");

                System.out.println("1. Add product to cart");
                System.out.println("2. Remove product from cart");
                System.out.println("3. Show cart");
                System.out.println("4. Checkout");
                System.out.println("0. Back");


                cartChoice = scanner.nextInt();

                switch (cartChoice) {
                    case 1 -> addToCart(user);

                    case 2 -> removeProductFromCart(user);

                    case 3 -> showCart(user);

                    case 4 -> checkout(user);

                    case 0 -> menu.mainMenu();

                    default -> System.out.println("Please use a valid option (0 - 4).");
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Please use a valid option. Input should be a number.");
            scanner.nextLine();
        }
    }

    // Add to cart

    private void addToCart(UserModel user) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product ID to add to your cart:");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        ProductModel productToAdd = null;
        for (ProductModel product : productDao.getList()) {
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

    // Show Cart

    private void showCart(UserModel user) throws IOException {
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

    // Remove item from cart

    private void removeProductFromCart(UserModel user) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the product you wish to remove from your cart:");
        int productId = scanner.nextInt();

        user.removeFromCart(productId);
    }

    // Checkout

    private void checkout(UserModel user) throws IOException {
        Scanner scanner = new Scanner(System.in);
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

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nA receipt will be sent shortly via email: ");
        System.out.println(user.getEmail() + "\n");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        }
        user.getShoppingCart().clear();
        menu.mainMenu();
    }
}
