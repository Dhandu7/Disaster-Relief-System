package edu.ucalgary.oop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class InquirerInterfaceTest {
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
    public void testSearchDisasterVictimsLocally() {
        String input = "Doe\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        List<DisasterVictim> victims = new ArrayList<>();
        victims.add(new DisasterVictim("John", "Doe", "1990-01-01", "2024-03-01"));

        InquirerInterface.searchDisasterVictimsLocally("doe");
        assertTrue(outContent.toString().contains("John Doe"));
    }

    @Test
    public void testSearchDisasterVictimsCentrally() {
        String input = "Doe\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        List<DisasterVictim> victims = new ArrayList<>();
        victims.add(new DisasterVictim("John", "Doe", "1990-01-01", "2024-03-01"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        InquirerInterface.searchDisasterVictimsCentrally("doe");
        assertTrue(outputStream.toString().contains("John Doe"));
    }

}
