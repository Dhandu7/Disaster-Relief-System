package edu.ucalgary.oop;

import java.util.Scanner;

public class DisasterVictimInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Disaster Victim Information System");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Enter new Disaster Victim");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    enterDisasterVictim();
                    break;
                case 2:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void enterDisasterVictim() { // Change access modifier to package-private
        System.out.println("\nEnter details for the new Disaster Victim:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Date of Entry (YYYY-MM-DD): ");
        String dateOfEntry = scanner.nextLine();

        DisasterVictim victim = new DisasterVictim(firstName, lastName, dateOfBirth, dateOfEntry);

        // Optionally, you can perform additional actions here, such as saving the
        // victim to a database or displaying the entered information
        System.out.println("\nDisaster Victim entered successfully:");
        System.out.println("Name: " + victim.getFirstName() + " " + victim.getLastName());
        System.out.println("Date of Birth: " + victim.getDateOfBirth());
        System.out.println("Date of Entry: " + victim.getEntryDate());
    }

    public static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

}
