//Kajan ranabhat - 20036997
//Subash dahal :20036488
//Nischal Thapa : 20038090


package com.ict711.app;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManagementSystem system = new UserManagementSystem();
    private static final FileManager fileManager = new FileManager();
    private static final String DATA_FILE = "data/users.csv";

    public static void main(String[] args) {
        loadInitialData();
        runMenu();
    }

    private static void loadInitialData() {
        try {
            system.setUsers(fileManager.loadUsers(DATA_FILE));
            System.out.println("Initial data loaded successfully from " + DATA_FILE);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load initial data: " + e.getMessage());
        }
    }

    private static void runMenu() {
        int choice = -1;
        while (choice != 8) {
            printMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> addUser();
                    case 2 -> viewUsers();
                    case 3 -> updateUser();
                    case 4 -> deleteUser();
                    case 5 -> evaluateUser();
                    case 6 -> system.displayTransactions();
                    case 7 -> saveUsers();
                    case 8 -> System.out.println("Exiting application. Goodbye.");
                    default -> System.out.println("Please choose a valid menu option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== User Performance Management System =====");
        System.out.println("1. Add user");
        System.out.println("2. View/query users");
        System.out.println("3. Update user");
        System.out.println("4. Delete user");
        System.out.println("5. Run periodic evaluation");
        System.out.println("6. View transaction history");
        System.out.println("7. Save data to file");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addUser() {
        try {
            System.out.print("Enter user type (Standard/Premium): ");
            String type = scanner.nextLine();
            System.out.print("Enter user ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter department: ");
            String department = scanner.nextLine();
            System.out.print("Enter performance score: ");
            int score = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter wallet balance: ");
            int wallet = Integer.parseInt(scanner.nextLine());

            User user = type.equalsIgnoreCase("Premium")
                    ? new PremiumUser(id, name, email, department, score, wallet, "Active")
                    : new StandardUser(id, name, email, department, score, wallet, "Active");

            if (system.addUser(user)) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("User ID already exists.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric value entered.");
        }
    }

    private static void viewUsers() {
        System.out.println("1. View all users");
        System.out.println("2. Query by ID");
        System.out.println("3. Query by name");
        System.out.print("Choose view option: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1" -> system.displayAllUsers();
            case "2" -> {
                System.out.print("Enter ID: ");
                User user = system.findUserById(scanner.nextLine());
                System.out.println(user == null ? "User not found." : user);
            }
            case "3" -> {
                System.out.print("Enter name keyword: ");
                List<User> results = system.findUsersByName(scanner.nextLine());
                if (results.isEmpty()) {
                    System.out.println("No matching users found.");
                } else {
                    for (User user : results) {
                        System.out.println(user);
                    }
                }
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void updateUser() {
        System.out.print("Enter the ID of the user to update: ");
        String id = scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new department: ");
        String department = scanner.nextLine();

        if (system.updateUser(id, name, email, department)) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deleteUser() {
        System.out.print("Enter the ID of the user to delete: ");
        String id = scanner.nextLine();
        if (system.deleteUser(id)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void evaluateUser() {
        try {
            System.out.print("Enter the ID of the user to evaluate: ");
            String id = scanner.nextLine();
            System.out.print("Enter score change for the month (e.g. 12 or -8): ");
            int scoreChange = Integer.parseInt(scanner.nextLine());

            EvaluationResult result = system.evaluateUser(id, scoreChange);
            if (result == null) {
                System.out.println("User not found.");
                return;
            }

            System.out.println("Evaluation completed.");
            System.out.println("Feedback: " + result.getFeedback());
            System.out.println("Transaction amount: " + result.getTransactionAmount());
            System.out.println("Updated score: " + result.getUpdatedScore());
            System.out.println("Updated status: " + result.getUpdatedStatus());
        } catch (NumberFormatException e) {
            System.out.println("Invalid score value entered.");
        }
    }

    private static void saveUsers() {
        try {
            fileManager.saveUsers(DATA_FILE, system.getUsers());
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving file: " + e.getMessage());
        }
    }
}
