package edu.ucalgary.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InquirerInterfaceTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testEnterInquiryLog() {
        String input = "1\nJohn Doe\n123\nTest details\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String result = InquirerInterface.enterInquiryLog();

        String expectedOutput = "Inquiry logged successfully.";
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testSearchDisasterVictimsLocally() {
        String input = "New York\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.searchDisasterVictimsLocally("John");

        String expectedOutput = "\nSearch Disaster Victims Locally:\n" +
                "Enter location for the search: \n" +
                "No matching Disaster Victims found locally in the specified location.\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testSearchDisasterVictimsCentrally() {
        InquirerInterface.searchDisasterVictimsCentrally("John");

        String expectedOutput = "\nSearch Disaster Victims Centrally:\n" +
                "No matching Disaster Victims found centrally.\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
