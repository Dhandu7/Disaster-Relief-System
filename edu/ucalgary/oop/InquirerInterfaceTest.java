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
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String input = "1\n123\nTest details\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.enterInquiryLog();

        String expectedOutput = "Enter Inquirer (ID): Enter Details: Inquiry logged successfully.\nInquiry logged successfully.";
        expectedOutput = expectedOutput.trim(); // Trim whitespace

        String actualOutput = outputStreamCaptor.toString().trim(); // Trim whitespace

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSearchDisasterVictimsLocally() {
        String input = "location\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.searchDisasterVictimsLocally("searchQuery");

        String expectedOutput = "\nSearch Disaster Victims Locally:\n" +
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

    @Test
    public void testLocalInquirerInterface() {
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.localInquirerInterface();

        String expectedOutput = "\nSelect an option:\n" +
                "1. Search Disaster Victims Locally\n" +
                "2. Add Disaster Victim\n" +
                "3. Add Family Relation\n" +
                "4. Add Medical Record\n" +
                "5. Go Back\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testCentralInquirerInterface() {
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.centralInquirerInterface();

        String expectedOutput = "\nSelect an option:\n" +
                "1. Search Disaster Victims Centrally\n" +
                "2. Log Inquiry\n" +
                "3. Go Back\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }

    @Test
    public void testEnterDisasterVictim() {
        String input = "John\nDoe\n2000-01-01\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.enterDisasterVictim();

        // Assert the output if needed
    }

    @Test
    public void testEnterFamilyRelation() {
        String input = "VictimFirstName\nVictimLastName\nRelationFirstName\nRelationLastName\nRelationship\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.enterFamilyRelation();

        // Assert the output if needed
    }

    @Test
    public void testEnterMedicalRecord() {
        String input = "Location\nFirstName\nLastName\nTreatmentDetail\nDateOfTreatment\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InquirerInterface.enterMedicalRecord();

        // Assert the output if needed
    }

}
