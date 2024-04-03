package edu.ucalgary.oop;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DisasterVictimInterfaceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testEnterDisasterVictim() {
        String input = "John\nDoe\n1990-01-01\n2024-03-01\nKSML\nLocationName\nTreatmentDetails\n2024-03-01\nRelatedFirstName\nRelatedLastName\nRelationship\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        DisasterVictimInterface.enterDisasterVictim();

        String output = outContent.toString();

        // Verify if the output contains the expected messages
        assertTrue(output.contains("Disaster Victim entered successfully:"));
        assertTrue(output.contains("Medical Record added successfully:"));
        assertTrue(output.contains("Relationship added successfully:"));

        // Verify if the output contains specific details
        assertTrue(output.contains("Name: John Doe"));
        assertTrue(output.contains("Date of Birth: 1990-01-01"));
        assertTrue(output.contains("Date of Entry: 2024-03-01"));
        assertTrue(output.contains("Location: LocationName"));
        assertTrue(output.contains("Treatment Details: TreatmentDetails"));
        assertTrue(output.contains("Date of Treatment: 2024-03-01"));
        assertTrue(output.contains("Related Person: RelatedFirstName RelatedLastName"));
        assertTrue(output.contains("Relationship to Victim: Relationship"));
    }
}
