package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class DisasterVictimTest {

    private DisasterVictim victim;
    private String expectedFirstName = "John";
    private String expectedLastName = "Doe";
    private String expectedDateOfBirth = "1990-01-01";
    private String expectedEntryDate = "2024-01-01";
    private Location testLocation;

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, expectedLastName, expectedDateOfBirth, expectedEntryDate);
        testLocation = new Location("Test Location", "123 Test St");
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(victim);
    }

    @Test
    public void testConstructorWithGender() {
        String firstName = "John";
        String lastName = "Doe";
        String dateOfBirth = "1990-05-15";
        String entryDate = "2024-03-08";
        String gender = "man";

        DisasterVictim victim = new DisasterVictim(firstName, lastName, dateOfBirth, entryDate, gender);

        assertEquals(firstName, victim.getFirstName());
        assertEquals(lastName, victim.getLastName());
        assertEquals(dateOfBirth, victim.getDateOfBirth());
        assertEquals(entryDate, victim.getEntryDate());
        assertEquals(gender, victim.getGender());
    }

    @Test
    public void testGetAssignedSocialID() {
        // The next victim should have an ID one higher than the previous victim
        // Tests can be run in any order so two victims will be created
        DisasterVictim newVictim = new DisasterVictim("Kash", "Doe", "1997-09-09", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "Doe", "1970-09-08", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId,
                actualVictim.getAssignedSocialID());
    }

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate() should return the entry date", expectedEntryDate, victim.getEntryDate());
    }

    @Test
    public void testGetGender() {
        assertNull("getGender() should return null initially", victim.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGender() {
        String firstName = "John";
        String lastName = "Doe";
        String dateOfBirth = "1990-05-15";
        String entryDate = "2024-03-08";
        String gender = "invalid";

        DisasterVictim victim = new DisasterVictim(firstName, lastName, dateOfBirth, entryDate, gender);
    }

    @Test
    public void testSetAndGetGender() {
        victim.setGender("man");
        assertEquals("setGender() should update the gender", "man", victim.getGender());
    }

    @Test
    public void testGetComments() {
        assertNull("getComments() should return null initially", victim.getComments());
    }

    @Test
    public void testSetComments() {
        String expectedComments = "Test comments";
        victim.setComments(expectedComments);
        assertEquals("setComments() should update the comments", expectedComments, victim.getComments());
    }

    @Test
    public void testAddPersonalBelonging() {
        Supply supply = new Supply("Water", 100);
        victim.addPersonalBelonging(supply);
        assertTrue("addPersonalBelonging() should add the specified supply to personal belongings",
                victim.getPersonalBelongings().contains(supply));
    }

    @Test
    public void testRemovePersonalBelonging() {
        Supply supply = new Supply("Water", 100);
        victim.addPersonalBelonging(supply);
        victim.removePersonalBelonging(supply);
        assertFalse("removePersonalBelonging() should remove the specified supply from personal belongings",
                victim.getPersonalBelongings().contains(supply));
    }

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2024-01-01");
        FamilyRelation relation = new FamilyRelation(victim, "Sibling", personTwo);
        victim.addFamilyConnection(relation);
        assertTrue("addFamilyConnection() should add the specified family relation",
                victim.getFamilyConnections().contains(relation));
    }

    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2024-01-01");
        FamilyRelation relation = new FamilyRelation(victim, "Sibling", personTwo);
        victim.addFamilyConnection(relation);
        victim.removeFamilyConnection(relation);
        assertFalse("removeFamilyConnection() should remove the specified family relation",
                victim.getFamilyConnections().contains(relation));
    }

    @Test
    public void testAllocateSupply() {
        Supply supply = new Supply("Water", 100);
        testLocation.addSupply(supply);
        victim.allocateSupply(supply, testLocation);
        assertFalse("allocateSupply() should remove the supply from the location's available supplies",
                testLocation.getSupplies().contains(supply));
        assertTrue("allocateSupply() should add the supply to the victim's personal belongings",
                victim.getPersonalBelongings().contains(supply));
    }
}
