package edu.ucalgary.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class InquirerInterfaceTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStreamCaptor = new ByteArrayInputStream("".getBytes());

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(inputStreamCaptor);
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void testEnterInquiryLog() {
        String input = "1\n123\nTest details\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.enterInquiryLog();

        String expectedOutput = "Enter Inquirer (ID): Enter Details: Inquiry logged successfully.\nInquiry logged successfully."
                + //
                "";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testSearchDisasterVictimsLocally() {
        String input = "location\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.searchDisasterVictimsLocally("searchQuery");

        String expectedOutput = "Search Disaster Victims Locally:\n" +
                "Enter location for the search: \n" +
                "No matching Disaster Victims found locally in the specified location.\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testSearchDisasterVictimsCentrally() {
        String input = "searchQuery\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.searchDisasterVictimsCentrally("searchQuery");

        String expectedOutput = "\nSearch Disaster Victims Centrally:\n" +
                "No matching Disaster Victims found centrally.\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    // Add more test cases for other methods as needed
}
