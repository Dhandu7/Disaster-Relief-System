package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InquirerInterface {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<DisasterVictim> victims = new ArrayList<>();

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
                    logInquiry();
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void logInquiry() {
        System.out.println("\nLog Inquiry:");
        System.out.print("Inquirer Name: ");
        String inquirerName = scanner.nextLine();

        // You can capture additional details such as contact information, nature of
        // inquiry, etc.

        // Log the inquiry (could be stored in a database or printed to console)
        System.out.println("Inquiry logged successfully.");
    }

    public static void searchDisasterVictimsLocally(String searchQueryLocal) {
        System.out.println("\nSearch Disaster Victims Locally:");

        // Perform search based on the search query locally
        List<DisasterVictim> searchResults = new ArrayList<>();
        for (DisasterVictim victim : victims) {
            if (victim.getFirstName().toLowerCase().contains(searchQueryLocal) ||
                    victim.getLastName().toLowerCase().contains(searchQueryLocal)) {
                searchResults.add(victim);
            }
        }

        // Display search results
        if (searchResults.isEmpty()) {
            System.out.println("No matching Disaster Victims found locally.");
        } else {
            System.out.println("Matching Disaster Victims Locally:");
            for (DisasterVictim victim : searchResults) {
                System.out.println(victim.getFirstName() + " " + victim.getLastName());
                // You can display additional information about each victim if needed
            }
        }
    }

    public static void searchDisasterVictimsCentrally(String searchQueryCentral) {
        System.out.println("\nSearch Disaster Victims Centrally:");

        // Perform search based on the search query centrally
        // Implement central search logic here
    }
}
