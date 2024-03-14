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

    public static void enterDisasterVictim() {
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

        System.out.println(
                "\nSelect dietary restrictions (separate with comma, e.g., DBML, GFML):\n\nAVML Asian vegetarian meal\nDBML Diabetic meal\nGFML Gluten intolerant meal\nKSML Kosher meal\nLSML Low salt meal\nMOML Muslim meal\nPFML Peanut-free meal\nVGML Vegan meal\nVJML Vegetarian Jain meal");
        String input = scanner.nextLine();
        String[] restrictions = input.split(",\\s*");
        for (String restriction : restrictions) {
            try {
                victim.addDietaryRestriction(DietaryRestriction.valueOf(restriction.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid dietary restriction: " + restriction);
            }
        }
        System.out.println("Dietary restrictions updated: " + victim.getDietaryRestrictions());

        System.out.println("\nDisaster Victim entered successfully:");
        System.out.println("Name: " + victim.getFirstName() + " " + victim.getLastName());
        System.out.println("Date of Birth: " + victim.getDateOfBirth());
        System.out.println("Date of Entry: " + victim.getEntryDate());

        // Enter medical record
        System.out.println("\nEnter details for the new Medical Record:");

        System.out.print("Location Name: ");
        String locationName = scanner.nextLine();

        System.out.print("Treatment Details: ");
        String treatmentDetails = scanner.nextLine();

        System.out.print("Date of Treatment (YYYY-MM-DD): ");
        String dateOfTreatment = scanner.nextLine();

        try {
            MedicalRecord medicalRecord = new MedicalRecord(new Location(locationName, ""), treatmentDetails,
                    dateOfTreatment);
            victim.addMedicalRecord(medicalRecord);
            System.out.println("\nMedical Record added successfully:");
            System.out.println("Location: " + medicalRecord.getLocation().getName());
            System.out.println("Treatment Details: " + medicalRecord.getTreatmentDetails());
            System.out.println("Date of Treatment: " + medicalRecord.getDateOfTreatment());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Enter relationships
        System.out.println("\nEnter details for the new Relationship:");

        System.out.print("Related Person's First Name: ");
        String relatedFirstName = scanner.nextLine();

        System.out.print("Related Person's Last Name: ");
        String relatedLastName = scanner.nextLine();

        System.out.print("Relationship to Victim: ");
        String relationship = scanner.nextLine();

        try {
            DisasterVictim relatedPerson = new DisasterVictim(relatedFirstName, relatedLastName, "", "");
            FamilyRelation familyRelation = new FamilyRelation(victim, relationship, relatedPerson);
            victim.addFamilyConnection(familyRelation);
            System.out.println("\nRelationship added successfully:");
            System.out.println("Related Person: " + relatedPerson.getFirstName() + " " + relatedPerson.getLastName());
            System.out.println("Relationship to Victim: " + familyRelation.getRelationshipTo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }
}
