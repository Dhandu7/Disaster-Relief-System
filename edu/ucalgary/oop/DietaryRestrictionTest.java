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
    public void testEnumDescriptions() {
        assertEquals("Asian vegetarian meal", DietaryRestriction.AVML.getDescription());
        assertEquals("Diabetic meal", DietaryRestriction.DBML.getDescription());
        assertEquals("Gluten intolerant meal", DietaryRestriction.GFML.getDescription());
        assertEquals("Kosher meal", DietaryRestriction.KSML.getDescription());
        assertEquals("Low salt meal", DietaryRestriction.LSML.getDescription());
        assertEquals("Muslim meal", DietaryRestriction.MOML.getDescription());
        assertEquals("Peanut-free meal", DietaryRestriction.PFML.getDescription());
        assertEquals("Vegan meal", DietaryRestriction.VGML.getDescription());
        assertEquals("Vegetarian Jain meal", DietaryRestriction.VJML.getDescription());
    }
}
