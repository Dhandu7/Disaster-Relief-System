package edu.ucalgary.oop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DietaryRestrictionTest {

    @Test
    public void testEnumValues() {
        assertEquals("AVML", DietaryRestriction.AVML.name());
        assertEquals("DBML", DietaryRestriction.DBML.name());
        assertEquals("GFML", DietaryRestriction.GFML.name());
        assertEquals("KSML", DietaryRestriction.KSML.name());
        assertEquals("LSML", DietaryRestriction.LSML.name());
        assertEquals("MOML", DietaryRestriction.MOML.name());
        assertEquals("PFML", DietaryRestriction.PFML.name());
        assertEquals("VGML", DietaryRestriction.VGML.name());
        assertEquals("VJML", DietaryRestriction.VJML.name());
    }
}