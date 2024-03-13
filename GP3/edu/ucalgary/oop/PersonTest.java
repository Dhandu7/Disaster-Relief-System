package edu.ucalgary.oop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {
    private String expectedFirstName = "Raju";
    private String expectedLastName = "Venketachalasharma";
    private String expectedDateOfBirth = "1925-12-07";
    private String invalidDate = "15/13/2024";

    @Before
    public void setUp() {
        Person victim = new Person(expectedFirstName, expectedLastName, expectedDateOfBirth);
    }

    @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth,
                victim.getDateOfBirth());
    }

}