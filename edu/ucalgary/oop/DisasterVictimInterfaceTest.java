package edu.ucalgary.oop;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

public class DisasterVictimInterfaceTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testEnterDisasterVictim() throws SQLException {
        // Prepare input for the test
        String input = "John\nDoe\n1990-01-01\n \n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DisasterVictimInterface.enterDisasterVictim();

        // Test output
        String expectedOutput = "\nEnter details for the new Disaster Victim:\nFirst Name: Last Name: Date of Birth (YYYY-MM-DD): AVML - Asian vegetarian meal\n"
                + //
                "DBML - Diabetic meal\n" + //
                "GFML - Gluten intolerant meal\n" + //
                "KSML - Kosher meal\n" + //
                "LSML - Low salt meal\n" + //
                "MOML - Muslim meal\n" + //
                "PFML - Peanut-free meal\n" + //
                "VGML - Vegan meal\n" + //
                "VJML - Vegetarian Jain meal\n" + //
                "Dietary Restrictions (separate with comma and use abbreviations) -- leave blank for none:Disaster victim added successfully.\n"
                + //
                "Disaster victim added successfully.\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testEnterFamilyRelation() throws SQLException {
        // Prepare input for the test
        String input = "John\nDoe\nJane\nDoe\nSibling\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Redirect output stream to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method to be tested
        DisasterVictimInterface.enterFamilyRelation();

        // Test output
        String expectedOutput = "\nEnter details for Person 1:\nFirst Name: Last Name: \nEnter details for Person 2:\nFirst Name: Last Name: Relation: Family relation added successfully.\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testEnterMedicalRecord() throws SQLException {
        // Prepare input for the test
        String input = "Location\nJohn\nDoe\nTreatment\n2024-01-01\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Redirect output stream to capture printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the method to be tested
        DisasterVictimInterface.enterMedicalRecord();

        // Test output
        String expectedOutput = "\nEnter the details of treatment:\nLocation: First Name: Last Name: Treatment: Date of Treatment: Medical record added successfully.\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testIsValidDateFormat() {
        assertTrue(DisasterVictimInterface.isValidDateFormat("2024-01-01"));
        assertFalse(DisasterVictimInterface.isValidDateFormat("2024/01/01"));
        assertFalse(DisasterVictimInterface.isValidDateFormat("01-01-2024"));
        assertFalse(DisasterVictimInterface.isValidDateFormat("2024/13/01"));
        assertFalse(DisasterVictimInterface.isValidDateFormat("2024/01/01"));

        assertFalse(DisasterVictimInterface.isValidDateFormat("2024-01-00"));
    }
}
