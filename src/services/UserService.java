package services;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

    private static List<UserModel> usersList = new ArrayList<>();
    private static int idCounter = 1;

    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            UserModel userModel = new UserModel();
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

            userModel.setId(idCounter++);
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

            usersList.add(userModel);

            System.out.println("User added successfully!\n");
        }
    }

    public void showUsers() {
        if (usersList.isEmpty()) {
            System.out.println("No users found\n");
        } else {
            System.out.println("All users: ");
            for (UserModel user : usersList) {
                System.out.println("User ID: " + user.getId() + " | Name: " + user.getName());
                System.out.println("Email address: " + user.getEmail() + " | Balance: " + user.getBalance());

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
            }
        }
    }

    public void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the account you wish to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        UserModel user = null;
        for (UserModel user2 : usersList) {
            if (user2.getId() == userId) {
                user = user2;
                break;
            }
        }

        if (user == null) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }
        System.out.println("Enter a new user name (or leave blank to keep current): ");
        String newUserName = scanner.nextLine();
        if (!newUserName.isEmpty()) {
            user.setName(newUserName);
        }

        System.out.println("Enter a new user email address (or leave blank to keep current): ");
        String newUserEmail = scanner.nextLine();
        if (!newUserEmail.isEmpty()) {
            user.setEmail(newUserEmail);
        }

        System.out.println("Enter a new balance for the user (or leave blank to keep current): ");
        String newBalance = scanner.nextLine();
        if (!newBalance.isEmpty()) {
            try {
                float balance = Float.parseFloat(newBalance);
                user.setBalance(balance);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. It should be a number. Keeping current.");
            }
        }

        AddressModel address = user.getAddress();
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

        System.out.println("User updated successfully!\n");
    }

    public void removeUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ID of the user that you wish to remove: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        UserModel removedUser = null;
        for (int i = 0; i < usersList.size(); i++) {
            UserModel user = usersList.get(i);
            if (user.getId() == userId) {
                removedUser = usersList.remove(i);
                break;
            }
        }

        if (removedUser == null) {
            System.out.println("User with ID " + userId + " not found.");
        } else {
            System.out.println("User removed successfully!\n");
        }
    }

    public UserModel findUserById(int id) {
        for (UserModel user : usersList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}


