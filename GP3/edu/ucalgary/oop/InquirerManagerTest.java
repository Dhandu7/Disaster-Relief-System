package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InquirerManagerTest {
    private InquirerManager inquirerManager;
    private Inquirer inquirer1;
    private Inquirer inquirer2;

    @Before
    public void setUp() {
        inquirerManager = new InquirerManager();
        inquirer1 = new Inquirer("John", "Doe", "1990-01-01", "+1-123-456-7890", "Looking for family");
        inquirer2 = new Inquirer("Jane", "Smith", "1985-05-15", "+1-987-654-3210", "Seeking information");
    }

    @Test
    public void testObjectCreation() {
        assertNotNull(inquirerManager);
    }

    @Test
    public void testAddInteraction() {
        inquirerManager.addInteraction(inquirer1, "Met with local authorities");
        inquirerManager.addInteraction(inquirer1, "Visited nearby shelters");

        assertEquals(2, inquirerManager.getInteractions(inquirer1).size());

        inquirerManager.addInteraction(inquirer2, "Contacted emergency services");
        assertEquals(1, inquirerManager.getInteractions(inquirer2).size());
    }

    @Test
    public void testGetInteractions() {
        assertEquals(0, inquirerManager.getInteractions(inquirer1).size());

        inquirerManager.addInteraction(inquirer1, "Received assistance from volunteers");
        inquirerManager.addInteraction(inquirer1, "Interviewed by media");

        assertEquals(2, inquirerManager.getInteractions(inquirer1).size());
    }
}
