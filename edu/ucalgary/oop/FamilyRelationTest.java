/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    @Test
    public void testEquals() {
        DisasterVictim peace = new DisasterVictim("Peace", "Dalan", "1999-09-09", "2024-01-19");
        DisasterVictim sam = new DisasterVictim("Sam", "Dalan", "2001-09-10", "2024-02-20");
        // Creating two FamilyRelation objects with different order of persons
        FamilyRelation relation1 = new FamilyRelation(peace, "sibling", sam);
        FamilyRelation relation2 = new FamilyRelation(sam, "sibling", peace);

        // Check if they are equal
        assertTrue(relation1.equals(relation2));
    }

    @Test
    public void testNotEquals() {
        DisasterVictim peace = new DisasterVictim("Peace", "Dalan", "1999-09-09", "2024-01-19");
        DisasterVictim sam = new DisasterVictim("Sam", "Dalan", "2001-09-10", "2024-02-20");
        DisasterVictim diamond = new DisasterVictim("Diamond", "Dalan", "2002-02-02", "2024-02-02");

        // Creating two different FamilyRelation objects
        FamilyRelation relation1 = new FamilyRelation(peace, "sibling", sam);
        FamilyRelation relation2 = new FamilyRelation(sam, "sibling", diamond);

        // Check if they are not equal
        assertFalse(relation1.equals(relation2));
    }
}
