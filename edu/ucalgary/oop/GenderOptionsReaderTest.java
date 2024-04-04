package edu.ucalgary.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GenderOptionsReaderTest {
    private GenderOptionsReader genderOptionsReader;

    @Before
    public void setUp() {
        genderOptionsReader = new GenderOptionsReader();
    }

    @Test
    public void testGenderOptionsAreLoadedFromFile() {
        List<String> genderOptions = genderOptionsReader.getGenderOptions();
        // Make sure the list is not empty
        assertTrue(genderOptions.size() > 0);
    }

    @Test
    public void testGetGenderOptions() {
        List<String> genderOptions = genderOptionsReader.getGenderOptions();
        assertNotNull(genderOptions);
        // Check that the size of the list matches the expected number of genders in the
        // file
        assertEquals(3, genderOptions.size()); // Change 3 to the expected size
    }
}
