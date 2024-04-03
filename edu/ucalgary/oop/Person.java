package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int age;

    public Person(String firstName, String lastName, String dateOfBirth) throws IllegalArgumentException {
        this.firstName = firstName;
        this.lastName = lastName;
        if (!isValidDateFormat(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date format: " + dateOfBirth);
        }
        this.dateOfBirth = dateOfBirth;
        this.age = ageCalculation(dateOfBirth);
    }

    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (!isValidDateFormat(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date format for date of birth. Expected format: YYYY-MM-DD");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public int ageCalculation(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
        } catch (Exception e) {
            // Handle parsing exceptions here
            System.err.println("Error calculating age: " + e.getMessage());
            return -1; // Return a default value indicating error
        }
    }
}
