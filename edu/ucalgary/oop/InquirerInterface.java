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
            System.out.println("2. Enter as Local Inquirer");
            System.out.println("3. Enter as Central Inquirer");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    enterInquiryLog();
                    break;
                case 2:
                    localInquirerInterface();
                    break;
                case 3:
                    centralInquirerInterface();
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

    public static void localInquirerInterface() {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Search Disaster Victims Locally");
            System.out.println("2. Add Disaster Victim");
            System.out.println("3. Add Family Relation");
            System.out.println("4. Add Medical Record");
            System.out.println("5. Go Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nEnter part of the name to search for locally: ");
                    String searchQueryLocal = scanner.nextLine().toLowerCase();
                    searchDisasterVictimsLocally(searchQueryLocal);
                    break;
                case 2:
                    enterDisasterVictim();
                    break;
                case 3:
                    enterFamilyRelation();
                    break;
                case 4:
                    enterMedicalRecord();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void centralInquirerInterface() {
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Search Disaster Victims Centrally");
            System.out.println("2. Log Inquiry");
            System.out.println("3. Go Back");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nEnter part of the name to search for centrally: ");
                    String searchQueryCentral = scanner.nextLine().toLowerCase();
                    searchDisasterVictimsCentrally(searchQueryCentral);
                    break;
                case 2:
                    enterInquiryLog();
                    break;
                case 3:
                    return;
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

            System.out.println("Inquiry logged successfully.");
        } catch (NumberFormatException e) {
            System.err.println("Invalid input for inquirer ID. Please enter a valid integer.");
        } catch (SQLException e) {
            System.err.println("Failed to get last inquirer ID.");
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

    public static void enterDisasterVictim() {
        System.out.println("\nEnter details for the new Disaster Victim:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print(DietaryRestriction.getDescriptions());
        System.out.print("Dietary Restrictions (separate with comma and use abbreviations) -- leave blank for none:");
        String dietaryRestrictions = scanner.nextLine();

        dbInstance.addDisasterVictim(firstName, lastName, dateOfBirth, dietaryRestrictions);
    }

    public static void enterFamilyRelation() {

        dbInstance.printDisasterVictimTable();
        System.out.println("\nEnter details for Person 1:");
        System.out.print("First Name: ");
        String victimfirstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String victimlastName = scanner.nextLine();

        System.out.println("\nEnter details for Person 2:");
        System.out.print("First Name: ");
        String relationfirstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String relationlastName = scanner.nextLine();

        System.out.print("Relation: ");
        String relationship = scanner.nextLine();

        dbInstance.addFamilyRelation(victimfirstName, victimlastName, relationfirstName, relationlastName,
                relationship);
    }

    public static void enterMedicalRecord() {
        System.out.println("\nEnter the details of treatment:");
        System.out.print("Location: ");
        String locationName = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Treatment: ");
        String treatment_detail = scanner.nextLine();

        System.out.print("Date of Treatment: ");
        String date_of_treatment = scanner.nextLine();

        dbInstance.addMedicalRecord(locationName, firstName, lastName, treatment_detail, date_of_treatment);
    }
}
