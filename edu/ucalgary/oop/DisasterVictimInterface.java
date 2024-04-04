package edu.ucalgary.oop;

import java.util.Scanner;
import java.sql.SQLException;

public class DisasterVictimInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static database dbInstance;

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
            System.out.println("1. Enter new Disaster Victim");
            System.out.println("2. Enter new Family Connection");
            System.out.println("3. Enter new Medical Record");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    enterDisasterVictim();
                    break;

                case 2:
                    enterFamilyRelation();
                    break;

                case 3:
                    enterMedicalRecord();
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

    public static void enterDisasterVictim() {
        System.out.println("\nEnter details for the new Disaster Victim:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        dbInstance.addDisasterVictim(firstName, lastName, dateOfBirth);

    }

    public static void enterFamilyRelation() {
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

        System.out.print("Treatment: ");
        String treatment_detail = scanner.nextLine();

        System.out.print("Date of Treatment: ");
        String date_of_treatment = scanner.nextLine();

        dbInstance.addMedicalRecord(locationName, treatment_detail, date_of_treatment);
    }

    public static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

}
