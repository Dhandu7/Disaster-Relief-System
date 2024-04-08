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

    @Test
    public void testGetDescriptions() {
        String expectedDescriptions = "AVML - Asian vegetarian meal\n" +
                "DBML - Diabetic meal\n" +
                "GFML - Gluten intolerant meal\n" +
                "KSML - Kosher meal\n" +
                "LSML - Low salt meal\n" +
                "MOML - Muslim meal\n" +
                "PFML - Peanut-free meal\n" +
                "VGML - Vegan meal\n" +
                "VJML - Vegetarian Jain meal\n";

        assertEquals(expectedDescriptions, DietaryRestriction.getDescriptions());
    }
}
