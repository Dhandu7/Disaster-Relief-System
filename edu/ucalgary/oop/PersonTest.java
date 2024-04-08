package edu.ucalgary.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
    private Person victim;
    private String expectedFirstName = "Raju";
    private String expectedLastName = "Venketachalasharma";
    private String expectedDateOfBirth = "1925-12-07";
    private String invalidDate = "15/13/2024";

    @Before
    public void setUp() {
        victim = new Person(expectedFirstName, expectedLastName, expectedDateOfBirth);
    }

    @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth,
                victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate); // This format should cause an exception
        fail("Expected an IllegalArgumentException to be thrown");
    }

    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName,
                victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName,
                victim.getLastName());
    }

    @Test
    public void testAgeCalculationWithZeroAge() {
        // Person born on the current date should have age 0
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Person person = new Person("John", "Doe", currentDate);
        assertEquals("Age should be 0 for a person born on the current date", 0, person.getAge());
    }

    @Test
    public void testAgeCalculationWithHighAge() {
        // Person born 100 years ago
        String birthDate = LocalDate.now().minusYears(100).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Person person = new Person("John", "Doe", birthDate);
        assertEquals("Age should be approximately 100 for a person born 100 years ago", 100, person.getAge(), 1);
    }

    @Test
    public void testConstructorWithValidAge() {
        int age = 25;
        Person person = new Person("John", "Doe", age);
        assertEquals("Constructor should correctly set the age", age, person.getAge());
    }
}
