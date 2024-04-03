package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.List;

public class GenderOptionsReaderTest {

    @Test
    public void testGetGenderOptions() {
        GenderOptionsReader genderOptionsReader = new GenderOptionsReader();
        List<String> genderOptions = genderOptionsReader.getGenderOptions();

        // Assuming the content of GenderOptions.txt is "boy", "gender queer", "girl",
        // "man", "non-binary", "two-spirit", "woman"
        assertEquals(7, genderOptions.size());
        assertEquals("boy", genderOptions.get(0));
        assertEquals("gender queer", genderOptions.get(1));
        assertEquals("girl", genderOptions.get(2));
        assertEquals("man", genderOptions.get(3));
        assertEquals("non-binary", genderOptions.get(4));
        assertEquals("two-spirit", genderOptions.get(5));
        assertEquals("woman", genderOptions.get(6));
    }
}
