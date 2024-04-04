package edu.ucalgary.oop;

import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.Date;

public class InquirerInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static final database dbInstance;

    static {
        try {
            dbInstance = new database();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database connection.", e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Disaster Victim Information System");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Log Inquiry");
            System.out.println("2. Search Disaster Victims Locally");
            System.out.println("3. Search Disaster Victims Centrally");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    enterInquiryLog();
                    break;
                case 2:
                    System.out.println("\nEnter part of the name to search for locally: ");
                    String searchQueryLocal = scanner.nextLine().toLowerCase();
                    searchDisasterVictimsLocally(searchQueryLocal);
                    break;
                case 3:
                    System.out.println("\nEnter part of the name to search for centrally: ");
                    String searchQueryCentral = scanner.nextLine().toLowerCase();
                    searchDisasterVictimsCentrally(searchQueryCentral);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    try {
                        dbInstance.close();
                    } catch (SQLException e) {
                        System.err.println("Failed to close database connection:");
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void enterInquiryLog() {
        try {
            Date currentDate = new Date();
            // Convert the current date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

            int id = dbInstance.getLastInquiryId() + 1;

            System.out.print("Enter Inquirer (ID): ");
            int inquirer = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Details: ");
            String details = scanner.nextLine();

            dbInstance.logInquiry(id, inquirer, sqlDate, details);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input for inquirer ID. Please enter a valid integer.");
        } catch (SQLException e) {
            System.err.println("Failed to get last inquirer ID:");
            e.printStackTrace();
        }
    }

    public static void searchDisasterVictimsLocally(String searchQueryLocal) {
        System.out.println("\nSearch Disaster Victims Locally:");

        System.out.println("Enter location for the search: ");
        String location = scanner.nextLine();

        List<String> searchResults = dbInstance.searchDisasterVictimsLocally(searchQueryLocal, location);

        if (searchResults.isEmpty()) {
            System.out.println("No matching Disaster Victims found locally in the specified location.");
        } else {
            System.out.println("Matching Disaster Victims Locally in the specified location:");
            for (String victim : searchResults) {
                System.out.println(victim);
            }
        }
    }

    public static void searchDisasterVictimsCentrally(String searchQueryCentral) {
        System.out.println("\nSearch Disaster Victims Centrally:");

        List<String> searchResults = dbInstance.searchDisasterVictimsCentrally(searchQueryCentral);

        if (searchResults.isEmpty()) {
            System.out.println("No matching Disaster Victims found centrally.");
        } else {
            System.out.println("Matching Disaster Victims Centrally:");
            for (String victim : searchResults) {
                System.out.println(victim);
            }
        }
    }
}
