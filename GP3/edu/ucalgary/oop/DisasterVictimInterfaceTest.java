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
    private final String input = "John\nDoe\n1990-01-01\n2024-03-01";
    private final String expectedOutput = "Welcome to the Disaster Victim Information System\n" +
            "\n" +
            "Select an option:\n" +
            "1. Enter new Disaster Victim\n" +
            "2. Exit\n" +
            "\n" +
            "Enter details for the new Disaster Victim:\n" +
            "First Name: Last Name: Date of Birth (YYYY-MM-DD): Date of Entry (YYYY-MM-DD): \n" +
            "Disaster Victim entered successfully:\n" +
            "Name: John Doe\n" +
            "Date of Birth: 1990-01-01\n" +
            "Date of Entry: 2024-03-01\n";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testEnterDisasterVictim() {
        String input = "John\nDoe\n1990-01-01\n2024-03-01\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        DisasterVictimInterface.enterDisasterVictim();
        String expectedOutput = "Disaster Victim entered successfully:";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

}
