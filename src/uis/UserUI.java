package uis;

import services.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserUI {

    UserService userService = new UserService();

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
                scanner.nextLine();

                if (userChoice == 1) {
                    userService.createUser();
                } else if (userChoice == 2) {
                    userService.updateUser();
                } else if (userChoice == 3) {
                    userService.removeUser();
                } else if (userChoice == 4) {
                    userService.showUsers();
                } else if (userChoice == 0) {
                    menu.mainMenu();
                }

                if (userChoice < 0 || userChoice > 4) {
                    System.out.println("Please use a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please use a valid option");
            } finally {
                userUI();
            }
        }

    }
}
