package edu.ucalgary.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DisasterVictimInterfaceTest {
    private final InputStream systemIn = System.in;
    private final InputStream userInput = new ByteArrayInputStream("".getBytes());
    private final databaseStub mockDatabase = new databaseStub();

    @Before
    public void setUp() {
        System.setIn(userInput);
        DisasterVictimInterface.setDatabase(mockDatabase);
    }

    @After
    public void tearDown() {
        System.setIn(systemIn);
    }

    @Test
    public void testEnterDisasterVictim() {
        String input = "John\nDoe\n1990-01-01\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DisasterVictimInterface.enterDisasterVictim();

        assertEquals("John", mockDatabase.firstName);
        assertEquals("Doe", mockDatabase.lastName);
        assertEquals("1990-01-01", mockDatabase.dateOfBirth);
    }

    @Test
    public void testEnterFamilyRelation() {
        String input = "John\nDoe\nJane\nDoe\nSpouse\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DisasterVictimInterface.enterFamilyRelation();

        assertEquals("John", mockDatabase.victimFirstName);
        assertEquals("Doe", mockDatabase.victimLastName);
        assertEquals("Jane", mockDatabase.relatedFirstName);
        assertEquals("Doe", mockDatabase.relatedLastName);
        assertEquals("Spouse", mockDatabase.relationship);
    }

    @Test
    public void testEnterMedicalRecord() {
        String input = "Hospital A\nTreatment ABC\n2024-03-08\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        DisasterVictimInterface.enterMedicalRecord();

        assertEquals("Hospital A", mockDatabase.locationName);
        assertEquals("Treatment ABC", mockDatabase.treatmentDetails);
        assertEquals("2024-03-08", mockDatabase.dateOfTreatment);
    }

    @Test
    public void testIsValidDateFormat() {
        assertTrue(DisasterVictimInterface.isValidDateFormat("2024-03-08"));
        assertTrue(DisasterVictimInterface.isValidDateFormat("2024-12-31"));
        assertTrue(DisasterVictimInterface.isValidDateFormat("2024-02-29"));
    }

    @Test
    public void testMainExitOption() {
        System.setIn(new ByteArrayInputStream("4\n".getBytes()));

        DisasterVictimInterface.main(null);
        assertEquals(1, mockDatabase.closeCount);
    }

    // Stub class for database
    private static class databaseStub extends database {
        String firstName;
        String lastName;
        String dateOfBirth;
        String victimFirstName;
        String victimLastName;
        String relatedFirstName;
        String relatedLastName;
        String relationship;
        String locationName;
        String treatmentDetails;
        String dateOfTreatment;
        int closeCount;

        @Override
        public void addDisasterVictim(String firstName, String lastName, String dateOfBirth) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public void addFamilyRelation(String victimFirstName, String victimLastName, String relatedFirstName,
                String relatedLastName, String relationship) {
            this.victimFirstName = victimFirstName;
            this.victimLastName = victimLastName;
            this.relatedFirstName = relatedFirstName;
            this.relatedLastName = relatedLastName;
            this.relationship = relationship;
        }

        @Override
        public void addMedicalRecord(String locationName, String treatmentDetails, String dateOfTreatment) {
            this.locationName = locationName;
            this.treatmentDetails = treatmentDetails;
            this.dateOfTreatment = dateOfTreatment;
        }

        @Override
        public void close() {
            closeCount++;
        }
    }
}
