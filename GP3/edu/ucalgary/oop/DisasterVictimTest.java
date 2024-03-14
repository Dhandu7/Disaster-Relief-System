package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class DisasterVictimTest {
    private DisasterVictim victim;
    private ArrayList<Supply> suppliesToSet;
    private ArrayList<FamilyRelation> familyRelations;
    private String expectedFirstName = "Raju";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDate = "2024-01-15";
    private String invalidDate = "15/13/2024";
    private String expectedGender = "female";
    private String expectedComments = "Needs medical attention and speaks 2 languages";

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, "Bhatti", "1992-09-09", EXPECTED_ENTRY_DATE);
        suppliesToSet = new ArrayList<>();
        suppliesToSet.add(new Supply("Water Bottle", 10));
        suppliesToSet.add(new Supply("Blanket", 5));

        DisasterVictim victim1 = new DisasterVictim("John", "Doe", "2009-01-01", EXPECTED_ENTRY_DATE);
        DisasterVictim victim2 = new DisasterVictim("Smith", "Doe", "2006-01-15", EXPECTED_ENTRY_DATE);

    }

    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", "Doe", "2000-01-01", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
        new DisasterVictim("Freda", "Doe", "2000-01-01", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date format
    }

    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    @Test
    public void testGetAssignedSocialID() {
        // The next victim should have an ID one higher than the previous victim
        // Tests can be run in any order so two victims will be created
        DisasterVictim newVictim = new DisasterVictim("Kash", "Doe", "1992-05-05", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "Doe", "1978-01-01", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId,
                actualVictim.getAssignedSocialID());
    }

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }

    @Test
    public void testSetAndGetGender() {
        String newGender = "male";
        victim.setGender(newGender);
        assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(),
                victim.getGender());
    }

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "Doe", "1999-09-09", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "Doe", "1992-02-22", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
        victim2.addFamilyConnection(relation);

        FamilyRelation[] testFamily = victim2.getFamilyConnections().toArray(new FamilyRelation[0]);
        boolean correct = false;

        if ((testFamily != null) && (testFamily[0].equals(relation))) {
            correct = true;
        }
        assertTrue("addFamilyConnection should add a family relationship", correct);
    }

    @Test
    public void testAddPersonalBelonging() {
        Supply newSupply = new Supply("Emergency Kit", 1);
        victim.addPersonalBelonging(newSupply);
        ArrayList<Supply> testSupplies = victim.getPersonalBelongings();
        boolean correct = false;

        if (testSupplies != null && testSupplies.contains(newSupply)) {
            correct = true;
        }

        assertTrue("addPersonalBelonging should add the supply to personal belongings", correct);
    }

    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "Doe", "1999-09-09", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "Doe", "1992-02-22", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim1, "parent", victim2);
        FamilyRelation relation2 = new FamilyRelation(victim2, "child", victim1);

        victim1.addFamilyConnection(relation1);
        victim2.addFamilyConnection(relation2);

        victim1.removeFamilyConnection(relation1);

        assertEquals("removeFamilyConnection should remove the specified family relationship", 0,
                victim1.getFamilyConnections().size());
    }

}