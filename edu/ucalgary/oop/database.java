package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

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

    public void addDisasterVictim(String firstName, String lastName, String dateOfBirth, String dietaryRestrictions) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "INSERT INTO disastervictim (first_name, last_name, birth_date, dietary_restrictions) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, dateOfBirth);
            preparedStatement.setString(4, dietaryRestrictions);
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
        try (PreparedStatement statement = dbConnect.prepareStatement(
                "INSERT INTO familyrelation (first_name1, last_name1, first_name2, last_name2, relationship_type) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, relatedFirstName);
            statement.setString(2, relatedLastName);
            statement.setString(3, victimFirstName);
            statement.setString(4, victimLastName);
            statement.setString(5, relationship);
            statement.executeUpdate();
            System.out.println("Family relation added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add family relation:");
            e.printStackTrace();
        }
    }

    public void addMedicalRecord(String locationName, String first_name, String last_name, String treatmentDetails,
            String dateOfTreatment) {
        try (PreparedStatement statement = dbConnect.prepareStatement(
                "INSERT INTO medicalrecord (location_name, first_name, last_name, treatment_detail, date_of_treatment) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, locationName);
            statement.setString(2, first_name);
            statement.setString(3, last_name);
            statement.setString(4, treatmentDetails);
            statement.setString(5, dateOfTreatment);
            statement.executeUpdate();
            System.out.println("Medical record added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add medical record:");
            e.printStackTrace();
        }
    }

    public void logInquiry(int id, int inquirer, Date calldate, String details) {
        try (PreparedStatement statement = dbConnect.prepareStatement(
                "INSERT INTO INQUIRY_LOG(id, inquirer, calldate, details) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, id);
            statement.setInt(2, inquirer);
            statement.setDate(3, new java.sql.Date(calldate.getTime())); // Convert java.util.Date to java.sql.Date
            statement.setString(4, details);
            statement.executeUpdate();
            System.out.println("Inquiry logged successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to log inquiry:");
            e.printStackTrace();
        }
    }

    public int getLastInquiryId() throws SQLException {
        int lastId = 0;
        try (PreparedStatement statement = dbConnect.prepareStatement(
                "SELECT id FROM INQUIRY_LOG ORDER BY id DESC LIMIT 1");
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                lastId = resultSet.getInt("id");
            }
        }
        return lastId;
    }

    public List<String> searchDisasterVictimsLocally(String searchQueryLocal, String location) {
        List<String> searchResults = new ArrayList<>();
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "SELECT DISTINCT dv.first_name, dv.last_name " +
                        "FROM DISASTERVICTIM dv " +
                        "JOIN MEDICALRECORD mr ON dv.first_name = mr.first_name AND dv.last_name = mr.last_name " +
                        "WHERE (LOWER(dv.first_name) LIKE ? OR LOWER(dv.last_name) LIKE ?) " +
                        "AND LOWER(mr.location_name) = ?")) {
            preparedStatement.setString(1, "%" + searchQueryLocal.toLowerCase() + "%");
            preparedStatement.setString(2, "%" + searchQueryLocal.toLowerCase() + "%");
            preparedStatement.setString(3, location.toLowerCase());
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

    public void printDisasterVictimTable() {
        try {
            // Fetch all records from the DISASTERVICTIM table
            PreparedStatement statement = dbConnect.prepareStatement("SELECT * FROM DISASTERVICTIM");
            ResultSet resultSet = statement.executeQuery();

            // Print each record
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthDate = resultSet.getString("birth_date");
                String dietaryRestrictions = resultSet.getString("dietary_restrictions");

                System.out.println(firstName + ", " + lastName + ", " + birthDate + ", " + dietaryRestrictions);
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch DISASTERVICTIM table:");
            e.printStackTrace();
        }
    }

}
