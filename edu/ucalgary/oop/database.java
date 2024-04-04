package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class database implements AutoCloseable {

    private Connection dbConnect;
    public static final String DBURL = "jdbc:postgresql://localhost/ensf380project";
    public static final String USERNAME = "oop";
    public static final String PASSWORD = "ucalgary";

    public database() throws SQLException {
        dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
    }

    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to establish a connection to the database:");
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        if (dbConnect != null) {
            dbConnect.close();
        }
    }

    public void addDisasterVictim(String firstName, String lastName, String dateOfBirth) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "INSERT INTO disastervictim (first_name, last_name, birth_date) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, dateOfBirth);
            preparedStatement.executeUpdate();
            System.out.println("Disaster victim added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add disaster victim:");
            e.printStackTrace();
        }
    }

    public void addFamilyRelation(String victimFirstName, String victimLastName, String relatedFirstName,
            String relatedLastName, String relationship) {
        try (PreparedStatement statement = dbConnect.prepareStatement(
                "INSERT INTO familyrelation (first_name1, last_name1, first_name2, last_name2, relationship_type) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, victimFirstName);
            statement.setString(2, victimLastName);
            statement.setString(3, relatedFirstName);
            statement.setString(4, relatedLastName);
            statement.setString(5, relationship);
            statement.executeUpdate();
            System.out.println("Family relation added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add family relation:");
            e.printStackTrace();
        }
    }

    public void addMedicalRecord(String locationName, String treatmentDetails, String dateOfTreatment) {
        try (PreparedStatement statement = dbConnect.prepareStatement(
                "INSERT INTO medicalrecord (location_name, treatment_detail, date_of_treatment) VALUES (?, ?, ?)")) {
            statement.setString(1, locationName);
            statement.setString(2, treatmentDetails);
            statement.setString(3, dateOfTreatment);
            statement.executeUpdate();
            System.out.println("Medical record added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add medical record:");
            e.printStackTrace();
        }
    }

    // doesnt work
    public void logInquiry(String inquirerName, String inquiryDetails) {
        try (PreparedStatement statement = dbConnect.prepareStatement(
                "INSERT INTO inquiries (inquirer_name, inquiry_details) VALUES (?, ?)")) {
            statement.setString(1, inquirerName);
            statement.setString(2, inquiryDetails);
            statement.executeUpdate();
            System.out.println("Inquiry logged successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to log inquiry:");
            e.printStackTrace();
        }
    }

    // doesnt work
    public List<String> searchDisasterVictimsLocally(String searchQueryLocal, String location) {
        List<String> searchResults = new ArrayList<>();
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "SELECT first_name, last_name FROM disastervictim WHERE (lower(first_name) LIKE ? OR lower(last_name) LIKE ?) AND location = ?")) {
            preparedStatement.setString(1, "%" + searchQueryLocal + "%");
            preparedStatement.setString(2, "%" + searchQueryLocal + "%");
            preparedStatement.setString(3, location);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    searchResults.add(firstName + " " + lastName);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to search disaster victims locally:");
            e.printStackTrace();
        }
        return searchResults;
    }

    // doesnt work
    public List<String> searchDisasterVictimsCentrally(String searchQueryCentral) {
        List<String> searchResults = new ArrayList<>();
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "SELECT first_name, last_name FROM disastervictim WHERE lower(first_name) LIKE ? OR lower(last_name) LIKE ?")) {
            preparedStatement.setString(1, "%" + searchQueryCentral + "%");
            preparedStatement.setString(2, "%" + searchQueryCentral + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    searchResults.add(firstName + " " + lastName);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to search disaster victims centrally:");
            e.printStackTrace();
        }
        return searchResults;
    }
}
